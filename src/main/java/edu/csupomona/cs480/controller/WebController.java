package edu.csupomona.cs480.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.inference.TestUtils;

import org.apache.commons.io.IOCase;

import org.codehaus.plexus.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Range;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.UserManager;


/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {

	/**
	 * When the class instance is annotated with
	 * {@link Autowired}, it will be looking for the actual
	 * instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in
	 * the {@link App} class.
	 */
	@Autowired
	private UserManager userManager;

	/**
	 * This is a simple example of how the HTTP API works.
	 * It returns a String "OK" in the HTTP response.
	 * To try it, run the web application locally,
	 * in your web browser, type the link:
	 * 	http://localhost:8080/cs480/ping
	 */
	@RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
	String healthCheck() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "OK";
	}
	
	/**
	 * Amanda's method
	 * Return a simple string
	 */
	@RequestMapping(value = "/cs480/hi", method = RequestMethod.GET)
	String hello() {
		return "HI";
	}
	/**
	 * Amanda's method
	 * Returns the different range values
	 */
	@RequestMapping(value = "cs480/range", method = RequestMethod.GET)
	String range(){
		Range<Integer> range1 = Range.closed(3, 8);
        System.out.println(range1);
        
        Range<Integer> range2 = Range.openClosed(3, 8);
        System.out.println(range2);

        Range<Integer> range3 = Range.closedOpen(3, 8);
        System.out.println(range3);
        
        return range1.toString()+range2.toString()+range3.toString();
	}

	
	// Randy's Method
	@RequestMapping( value = "/cs4800/GitGud", method = RequestMethod.GET)
	String gitGud(){
		
		return  "Step 1 : Start game\r\n" + 
				"Step 2 : Create character\r\n" + 
				"Step 3 : Git gud\r\n" + 
				"Step 4 : Be the best player\r\n" + 
				"Step 5 : Rate this guide for teaching you how to achieve gudness\r\n" + 
				"Step 6 : Close the game\r\n" + 
				"Step 7 : Complain about Dark Souls 2 and 3 for not being as hard as Dark Souls 1 or for not having a good enough lore\r\n" + 
				"Step 8 : Secretly play those games and enjoy them\r\n" + 
				"Step 9 : Bash those that publicly display their love for those games\r\n" + 
				"Step 10 : Start game\r\n" + 
				"Step 11 : Create character\r\n" + 
				"Step 12 : Create a Giant Dad\r\n" + 
				"Step 13 : Kill scrubs in PVP\r\n" + 
				"Step 14 : Reach another plane of existance\r\n" + 
				"Step 15 : You finally gut gud";
	}
	
	
	@RequestMapping(value="/cs4800/string_compare", method = RequestMethod.GET)
	String compare()
	{
		String sentence1 = "Abandon All Hope Yee Who Enter Here";
		String sentence2 = "abandon all hope yee who enter here";	
;		
		return 	"This page will compare the case sensitivity of the following two sentences" +
				"<br>" + sentence1 +
			   	"<br>" + sentence2 +
				"<br>" +
			  	"<br>Are the sentences equal with regard to Case sensitivity:  " + IOCase.SENSITIVE.checkEquals(sentence1, sentence2) +
			  	"<br>Are the sentences equal with regard to Case insensitivity: " + IOCase.INSENSITIVE.checkEquals(sentence1, sentence2);

		
	}
		
		
	
	

	/**
	 * Method that prints a string
	 * @return String that is seen by the user
	 */
	@RequestMapping(value = "/cs4800/lance", method = RequestMethod.GET)
	String lance() {
		return "I am Lance";
	}

        /**
         * Method that prints a random string
         */
        @RequestMapping( value = "cs4800/random", method = RequestMethod.GET)
        String randomName() {
            String[] teamNames = new String[]{"Amanda", "Lance", "Connor", "Randy", "Steven"};
            Random rdm = new Random();
            int rng = rdm.nextInt(5);
            return teamNames[rng];
        }
        
    /**
     * Method that grabs all links from google.com
     */
    @RequestMapping(value = "/cs4800/getLinks", method = RequestMethod.GET)
    String linkGrab( ) {
    	String title = "";
    	Document doc;
		try {
			doc = Jsoup.connect("https://www.google.com").get();
			title = doc.title();
			Elements links = doc.select("a[href]");
			for(Element link : links) {
				title = title + "<br><br>Link : " + link.attr("href") + "<br>Text : " + link.text();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return title;
    }
    
    /**
     *Generates random double array, applies t-test to it
     * @throws Exception
     */
    @RequestMapping(value = "/cs4800/ttest", method = RequestMethod.GET)
    String testStatisticalTests() throws Exception {
        double[] x = new double[10];
        for (int i = 0; i < x.length; i++) {
            x[i] = Math.random()*100+1;
        }
        System.out.println("Testing array: " + Arrays.toString(x));
        //One-sample t-test
        double mu = new DescriptiveStatistics(x).getMean();
        return "Testing array: " + Arrays.toString(x)
                + "<br>Mean: " + mu 
                + "<br>t-statistic for true mean: " + TestUtils.t(50, x) 
                + "<br>p-value for true mean: " + TestUtils.tTest(50, x);
    }
	
	/**
	 * Connor's Method
	 * Method that print a simple math equation
	 */
	@RequestMapping(value = "/cs480/math", method = RequestMethod.GET)
	String simpleMath() 
	{
		return "1+1=2";
	}

	/**
	 * Connor's Method
	 * Capitalizes a string
	 */
	@RequestMapping(value = "/cs480/Rain", method = RequestMethod.GET)
	String caps()
	{
		return capitalise("rain");
	}

	/**
	 * This is a simple example of how to use a data manager
	 * to retrieve the data and return it as an HTTP response.
	 * <p>
	 * Note, when it returns from the Spring, it will be
	 * automatically converted to JSON format.
	 * <p>
	 * Try it in your web browser:
	 * 	http://localhost:8080/cs480/user/user101
	 */
	
	
	
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.GET)
	User getUser(@PathVariable("userId") String userId) {
		User user = userManager.getUser(userId);
		return user;
	}

	/**
	 * This is an example of sending an HTTP POST request to
	 * update a user's information (or create the user if not
	 * exists before).
	 *
	 * You can test this with a HTTP client by sending
	 *  http://localhost:8080/cs480/user/user101
	 *  	name=John major=CS
	 *
	 * Note, the URL will not work directly in browser, because
	 * it is not a GET request. You need to use a tool such as
	 * curl.
	 *
	 * @param id
	 * @param name
	 * @param major
	 * @return
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.POST)
	User updateUser(
			@PathVariable("userId") String id,
			@RequestParam("name") String name,
			@RequestParam(value = "major", required = false) String major) {
		User user = new User();
		user.setId(id);
		user.setMajor(major);
		user.setName(name);
		userManager.updateUser(user);
		return user;
	}

	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 *
	 * @param userId
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
	void deleteUser(
			@PathVariable("userId") String userId) {
		userManager.deleteUser(userId);
	}

	/**
	 * This API lists all the users in the current database.
	 *
	 * @return
	 */
	@RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
	ModelAndView getUserHomepage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}

}
