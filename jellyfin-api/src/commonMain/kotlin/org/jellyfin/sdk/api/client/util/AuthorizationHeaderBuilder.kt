package org.jellyfin.sdk.api.client.util

public object AuthorizationHeaderBuilder {
	public const val AUTHORIZATION_SCHEME: String = "MediaBrowser"
	private const val KEY_CLIENT = "Client"
	private const val KEY_VERSION = "Version"
	private const val KEY_DEVICE_ID = "DeviceId"
	private const val KEY_DEVICE = "Device"
	private const val KEY_TOKEN = "Token"

	public fun encodeParameterValue(raw: String): String = raw
		.trim()
		.replace(Regex("\\n"), " ")
		.encodeURLPart()

	public fun buildParameter(key: String, value: String): String {
		// Check for bad strings to prevent endless hours debugging why the server throws http 500 errors
		require(!key.contains('=')) {
			"Key $key can not contain the = character in the authorization header"
		}
		require(!key.contains(',')) {
			"Key $key can not contain the , character in the authorization header"
		}
		require(!key.startsWith('"') && !key.endsWith('"')) {
			"Key $key can not start or end with the \" character in the authorization header"
		}

		// key="value"
		return """${key}="${encodeParameterValue(value)}""""
	}

	public fun buildHeader(
		clientName: String,
		clientVersion: String,
		deviceId: String,
		deviceName: String,
		accessToken: String? = null,
	): String {
		val params: List<Pair<String, String>> = buildList {
			add(KEY_CLIENT to clientName)
			add(KEY_VERSION to clientVersion)
			add(KEY_DEVICE_ID to deviceId)
			add(KEY_DEVICE to deviceName)
			accessToken?.let { token ->
				add(KEY_TOKEN to token)
			}
		}

		// Format: `MediaBrowser key1="value1", key2="value2"`
		return params
			.joinToString(
				separator = ", ",
				prefix = "$AUTHORIZATION_SCHEME ",
				transform = { (key, value) -> buildParameter(key, value) }
			)
	}
}
