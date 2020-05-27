package com.java.jobscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JobscraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobscraperApplication.class, args);
	}

	public void run(String... args) throws Exception {

		String mainUrl = "https://www.naukri.com/deloitte-jobs-in-hyderabad";
		// hit the job url
		Document doc = Jsoup.connect(mainUrl).get();

		// initializing main jobdetails list
		List<Element> jobsList = new ArrayList<>();


		// check if next page exists
		boolean isNext = true;
		while (isNext) {
			// get job details from the page and populate the domain object
			List<Element> tempList = doc.getElementsByAttributeValue("id", "jdUrl");
			jobsList.addAll(tempList);

			isNext = checkNextPage(doc);
			if (!isNext) {
				break;
			}
			Element nextPageElement = doc.getElementsByAttributeValue("class", "pagination").get(0);
			String nextPageUrl = "";
			List<Node> childNodes = nextPageElement.childNodes();
			Optional<Node> nextButtonNode = childNodes.stream()
					.filter(node -> node.toString().toLowerCase().contains("next"))
					.findFirst();
			if (nextButtonNode.isPresent()) {
				Node childNode = nextButtonNode.get();
				nextPageUrl = childNode.attr("href");
			}
			doc = Jsoup.connect(nextPageUrl).get();
		}
		System.out.println(jobsList.size());
		jobsList.forEach(each-> System.out.println(each));
	}

	private boolean checkNextPage(Document doc) {
		Elements buttonElement = doc.getElementsByAttributeValue("class", "pagination");
		String text = buttonElement.text().toLowerCase();
		if (text.contains("next")) {
			return true;
		}
		return false;
	}

}
