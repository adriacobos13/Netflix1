package com.everis.d4i.tutorial.utils.constants;

public class RestConstants {

	public static final String APPLICATION_NAME = "/netflix";
	public static final String API_VERSION_1 = "/v1";
	public static final String SUCCESS = "Success";

	public static final String RESOURCE_CATEGORY = "/categories";
	public static final String RESOURCE_AWARD = "/awards";
	public static final String RESOURCE_ACTOR = "/actors";
	public static final String RESOURCE_TV_SHOW = "/tv-shows";
	public static final String RESOURCE_SEASON = "/tv-shows/{tvShowId}/seasons";
	public static final String RESOURCE_RATE = "/tv-shows/{tvShowId}/rates";
	public static final String RESOURCE_CHAPTER = "/tv-shows/{tvShowId}/seasons/{seasonNumber}/chapters";
	public static final String RESOURCE_ID = "/{id}";
	public static final String RESOURCE_NUMBER = "/{number}";
	
	public static final String PARAMETER_CATEGORY = "categories";
	public static final String PARAMETER_TV_SHOW = "tv-shows";
	public static final String PARAMETER_CHAPTER = "chapters";
	public static final String PARAMETER_ACTOR = "actors";

	private RestConstants() {
		throw new IllegalStateException("Utility Class");
	}

}
