package by.academy.springnews.service;

import by.academy.springnews.model.News;

public class NewsValidationService {

	private static final int MIN_LENGTH = 10;
	private static final int MAX_TITLE_LENGTH= 500;
	private static final int MAX_BRIEF_LENGTH = 3000;

	public NewsValidationService() {

	}
	public static boolean newsValidation(News news) {
		return (titleValidation(news.getTitle()) && briefValidation(news.getBrief()) && contentValidation(news.getContent()));
	}

	public static boolean titleValidation(String title) {
		return title.length() >= MIN_LENGTH && title.length() < MAX_TITLE_LENGTH;
	}

	public static boolean briefValidation(String brief) {
		return brief.length() >= MIN_LENGTH && brief.length() < MAX_BRIEF_LENGTH;
	}

	public static boolean contentValidation(String content) {
		return content.length() >= MIN_LENGTH;

	}

}
