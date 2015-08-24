package it.polimi.deib.blackboard_mediator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class StubBasedCPSMA {

	private static Logger logger = LoggerFactory
			.getLogger(StubBasedCPSMA.class);

	public static boolean validateSyntax(String filePath, String lang) {
		Model model = ModelFactory.createDefaultModel();

		InputStream in = FileManager.get().open(filePath);

		try {
			model.read(in, null, lang);
		} catch (Exception e) {
			logger.error("RDF syntax error", e);
			return false;
		}
		return true;
	}

	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws ParseException
	 * @throws BlackboardException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			ParseException, BlackboardException {
		
		final int TEST1 = 1;
		final int TEST2 = 2;

		int test = TEST2;
		
		BlackboardMediator slm = new BlackboardMediator(Agents.SL);

		switch (test) {
		case TEST1:

			// creates an instance of the BlackboardMediator class for the agent
			// SL


			int j = 1;
			while (j<100) {

				Calendar cal = Calendar.getInstance();
				Date begin = cal.getTime();
				for (int i = 0; i < 100; i++) {

					cal = Calendar.getInstance();
					if (validateSyntax(
							"src/main/resources/exampleOfHistoricalVmOutput.ttl",
							"TURTLE")) {
						String content = new Scanner(
								new File(
										"src/main/resources/exampleOfHistoricalVmOutput.ttl"))
								.useDelimiter("\\Z").next();
						slm.putNewGraph(cal.getTime(), content);
					}
				}

				Calendar cal1 = Calendar.getInstance();
				Date middle = cal1.getTime();
				System.out.print(j + "\t"
						+ (middle.getTime() - begin.getTime()) + "\t");

				List<String> l = slm.getRecentGraphNames(begin);

				// System.out.println(l);

				for (String s : l) {
					String responseStr = slm.getGraph(s);
					Model m = ModelFactory.createDefaultModel();
					m.read(new StringReader(responseStr), null, "TURTLE");
					// m.write(System.out);
				}
				Calendar cal2 = Calendar.getInstance();
				Date end = cal2.getTime();
				System.out.println(end.getTime() - middle.getTime());

				j++;

			}
			break;

		case TEST2:

			j = 1;
			while (j<10000) {

				Calendar cal = Calendar.getInstance();
				Date begin = cal.getTime();
				

					cal = Calendar.getInstance();
					if (validateSyntax(
							"src/main/resources/exampleOfHistoricalVmOutput.ttl",
							"TURTLE")) {
						String content = new Scanner(
								new File(
										"src/main/resources/exampleOfHistoricalVmOutput.ttl"))
								.useDelimiter("\\Z").next();
						slm.putNewGraph(cal.getTime(), content);
					}

				Calendar cal1 = Calendar.getInstance();
				Date middle = cal1.getTime();
				System.out.println(j + "\t"
						+ (middle.getTime() - begin.getTime()));

				j++;
			}			
			break;

		}
	}
}
