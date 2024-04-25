package application.model.local_impl.game;

public enum Genre {
	NONE, ACTION, ADVENTURE, ANIMATION_MODELING, ACCOUNTING, AUDIO_PRODUCTION, CASUAL, COMMENTARY_AVAILABLE, CO_OP,
	CROSS_PLATFORM_MULTIPLAYER, DESIGN_ILLUSTRATION, DOCUMENTARY, EARLY_ACCESS, EDUCATION, FREE_TO_PLAY,
	FULL_CONTROLLER_SUPPORT, GAME_DEVELOPMENT, GORE, HUNTERS, INCLUDES_LEVEL_EDITOR, INCLUDES_SOURCE_SDK, INDIE,
	IN_APP_PURCHASES, LINUX, LOCAL_CO_OP, LOCAL_MULTI_PLAYER, MAC, MASSIVELY_MULTIPLAYER, MMO, MISSING_GENRE,
	MULTI_PLAYER, NUDITY, ONLINE_CO_OP, ONLINE_MULTIPLAYER, PARTIAL_CONTROLLER_SUPPORT, PHOTO_EDITING, RACING, RPG,
	SEXUAL_CONTENT, SHARED_SPLIT_SCREEN, SIMULATION, SINGLE_PLAYER, SOFTWARE_TRAINING, SPORTS, STATS,
	STEAM_ACHIEVEMENTS, STEAM_CLOUD, STEAM_LEADERBOARDS, STEAM_TRADING_CARDS, STEAM_TURN_NOTIFICATIONS, STEAM_WORKSHOP,
	STRATEGY, TUTORIAL, UTILITIES, VALVE_ANTI_CHEAT_ENABLED, VIDEO_PRODUCTION, VIOLENT, VR_SUPPORT, WEB_PUBLISHING,
	WINDOWS;

	@Override
	public String toString() {
		String name = name();
		return toTitleCase(name);
	}

	private static String toTitleCase(String input) {
		StringBuilder titleCase = new StringBuilder();
		boolean nextTitleCase = true;

		for (char character : input.toCharArray()) {
			if (Character.isSpaceChar(character)) {
				nextTitleCase = true;
			} else if (nextTitleCase) {
				character = Character.toTitleCase(character);
				nextTitleCase = false;
			}

			titleCase.append(character);
		}
		return titleCase.toString();
	}
}
