package study.coco.csb.server.tools;

import study.coco.csb.server.Review;
import study.coco.csb.server.ReviewService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *  This simple HTTP Server was adapted from a tutorial, that can be found on the SSaurel's Blog:
 *  https://www.ssaurel.com/blog/create-a-simple-http-web-server-in-java
 *  
 *  Each Client Connection will be managed in a dedicated Thread
 *  Only the first request line is used to handle the request
 *
 */
public class JavaHTTPServer implements Runnable{ 

	// port to listen connection
	static final int PORT = 8090;

	// Client Connection via Socket Class
	private Socket connect;
	
	public JavaHTTPServer(Socket c) {
		connect = c;
		System.out.println("Connection opened (" + new Date() + "): " + c.getRemoteSocketAddress() + " --> " + c.getLocalSocketAddress() + "");
	}
	
	public static void main(String[] args) {
	    ServerSocket serverConnect = null;
		try {
			serverConnect = new ServerSocket(PORT);
			System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");
			
			// we listen until user halts server execution
			while (true) {
				JavaHTTPServer myServer = new JavaHTTPServer(serverConnect.accept());

				// create dedicated thread to manage the client connection
				Thread thread = new Thread(myServer);
				System.out.println("Thread Id: " + thread.getId());
				thread.start();
			}
			
		} catch (IOException e) {
			System.err.println("Server Connection error : " + e.getMessage());
		} finally {
		    try {
                serverConnect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

	@Override
	public void run() {
		// we manage our particular client connection
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
		    // we read characters from the client via input stream on the socket
			in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			
			// we get character output stream to client (for headers)
			out = new PrintWriter(connect.getOutputStream());
			
			// get first line of the request from the client
			String requestLine = in.readLine();
			
			// split request line into separate strings
			String[] requestLineTokens = requestLine.split(" ");
			
			// Simple validation of request line
			if (requestLineTokens.length != 3) {
			    System.err.println("Wrong requestLine format received.");
			    System.err.println(requestLine);
			}
			
			// map request line parts to method path and version
			String method = requestLineTokens[0];
			String path = requestLineTokens[1];
			String version = requestLineTokens[2];

			// we read all remaining headers till the first empty line
			ArrayList<String> headers = new ArrayList<>();
			do {
				requestLine = in.readLine();
				System.out.println(requestLine);
				headers.add(requestLine);
			} while (!requestLine.isEmpty());

			// we scan the header for additional content after the first empty line
			int contentLength = 0;
			for (String header : headers) {
				if (header.startsWith("Content-Length")) {
					contentLength = Integer.parseInt(header.split(" ")[1]);
				}
			}

			// Prepare char array with given content length and
			// read the body of the http request
			char[] body = new char[contentLength];
			in.read(body);

			String content = new String(body);
			System.out.println(content);

			// we handle the content as "application/x-www-form-urlencoded",
			// other Content-Types are not supported right now
			ArrayList<String> parameters = new ArrayList<>();
			for (String header : headers) {
				if (header.startsWith("Content-Type") &&
								(header.contains("application/x-www-form-urlencoded"))) {
					parameters.addAll(Arrays.asList(content.split("&")));
				}
			}

			System.out.println(parameters);

			// we currently support only GET method, we check
			if (method.equals("GET")) {
				// GET method
				String responseBody = "<!DOCTYPE html><html><head></head><body>200</body></html>";

				// We try to implement the same Spring Boot Magic,
				// that we configured in the ReviewController with
				// the RequestMapping: @GetMapping("/reviews")
				System.out.println("Path compare + " + path + " with reviews detected!");
				if (path.equals("/reviews")) {
					System.out.println("Path /reviews detected!");

					ReviewService service = new ReviewService();
					List<Review> reviews = service.getReviews();

					responseBody = reviews.toString();
				}

				// send HTTP Headers
				out.println("HTTP/1.1 200 OK");
				out.println("Server: Java HTTP Server from csb : 1.0");
				out.println("Date: " + new Date());
				out.println("Content-type: " + "text/html");
				out.println("Content-length: " + responseBody.getBytes("UTF-8").length);
				out.println(); // blank line between headers and content, very important !
				out.flush(); // flush character output stream buffer

				out.println(responseBody);
				out.flush();
				
				System.out.println("Response for path" + path + " of type text/html returned");

			} else if (method.equals("POST")) {
				// POST method

				// we extract the parameter for the name
				String name = "nobody";
				for (String parameter : parameters) {
					if (parameter.startsWith("name")) {
						name = parameter.split("=")[1];
					}
				}

				String responseBody = "<!DOCTYPE html><html><head></head><body>Hello, " + name + "!</body></html>";

				// send HTTP Headers
				out.println("HTTP/1.1 200 OK");
				out.println("Server: Java HTTP Server from csb : 1.0");
				out.println("Date: " + new Date());
				out.println("Content-type: " + "text/html");
				out.println("Content-length: " + responseBody.getBytes("UTF-8").length);
				out.println(); // blank line between headers and content, very important !
				out.flush(); // flush character output stream buffer

				out.println(responseBody);
				out.flush();

				System.out.println("Response for path" + path + " of type text/html returned");

			} else {
				// other HTTP methods
				System.out.println("501 Not Implemented : " + method + " method.");

				String responseBody = "<!DOCTYPE html><html><head></head><body>501</body></html>";

				// we send HTTP Headers with data to client
				out.println("HTTP/1.1 501 Not Implemented");
				out.println("Server: Java HTTP Server from csb : 1.0");
				out.println("Date: " + new Date());
				out.println("Content-type: " + "text/html");
				out.println("Content-length: " + responseBody.getBytes("UTF-8").length);
				out.println(); // blank line between headers and content, very important !
				out.flush(); // flush character output stream buffer
				// file
				out.println(responseBody);
				out.flush();
			}
			
		} catch (NullPointerException fnfe) {
			try {
				internalError(out);
			} catch (IOException ioe) {
				System.err.println("Error with exception : " + ioe.getMessage());
			}
			
		} catch (IOException ioe) {
			System.err.println("Server error : " + ioe);
		} finally {
			try {
				in.close();
				out.close();
				connect.close(); // we close socket connection
			} catch (Exception e) {
				System.err.println("Error closing stream : " + e.getMessage());
			} 
			
			System.out.println("Connection closed.\n");
		}
		
		
	}
	
	private void internalError(PrintWriter out) throws IOException {
		String responseBody = "<!DOCTYPE html><html><head></head><body>500</body></html>";
	    
		out.println("HTTP/1.1 500 Internal Server Error");
		out.println("Server: Java HTTP Server from csb : 1.0");
		out.println("Date: " + new Date());
		out.println("Content-type: " + "text/html");
		out.println("Content-length: " + responseBody.getBytes("UTF-8").length);
		out.println(); // blank line between headers and content, very important !
		out.flush(); // flush character output stream buffer
		
		out.println(responseBody);
		out.flush();
		
		System.out.println("Internal Error");
	}
	
}