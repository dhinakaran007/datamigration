package com.datamigration;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.datamigration.model.BleveApiResponseModel;
import com.datamigration.model.BleveRequestModel;
import com.datamigration.service.CsvUploadService;
import com.datamigration.service.DmService;

@SpringBootApplication
public class DatamigrationApplication implements CommandLineRunner {

	@Autowired
	DmService dmService;

	public static void main(String[] args) {
		SpringApplication.run(DatamigrationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application started successfully. Listening to 8080");
		System.out.println("Available APIs:");
		List<String> apiList = List.of(
				"1. Bleve search API: POST /api/bleve/search",
				"2. CSV Upload Service: Uploads CSV data to Bleve index",
				"0. Exit");

		Scanner scanner = new Scanner(System.in);

		try {
			boolean running = true;
			while (running) {
				apiList.forEach(System.out::println);
				System.out.println("Choose an option:");
				String input = scanner.nextLine();
				switch (input) {
					case "1":
						System.out.println("Calling Service One...");
						BleveRequestModel requestModel = new BleveRequestModel();
						requestModel.setIndexName("userIndex");
						requestModel.setPageSize(100);

						BleveApiResponseModel response = dmService.getDataFromBleve(requestModel);
						System.out.println("Response from Service One: " + response.getData());
						break;

					case "2":
						System.out.println("Calling Service Two... \n CsvUploadService");
						CsvUploadService csvUploadService = new CsvUploadService(dmService);
						csvUploadService.upload("E:/datamigration/src/main/java/com/datamigration/cache/en_name.csv");
						break;

					case "0":
						System.out.println("Exiting application...");
						running = false;
						break;

					default:
						System.out.println("Invalid option. Please try again.");
						break;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

}
