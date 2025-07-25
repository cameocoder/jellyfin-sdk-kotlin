// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.Int
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The trickplay api model.
 */
@Serializable
public data class TrickplayInfoDto(
	/**
	 * The width of an individual thumbnail.
	 */
	@SerialName("Width")
	public val width: Int,
	/**
	 * The height of an individual thumbnail.
	 */
	@SerialName("Height")
	public val height: Int,
	/**
	 * The amount of thumbnails per row.
	 */
	@SerialName("TileWidth")
	public val tileWidth: Int,
	/**
	 * The amount of thumbnails per column.
	 */
	@SerialName("TileHeight")
	public val tileHeight: Int,
	/**
	 * The total amount of non-black thumbnails.
	 */
	@SerialName("ThumbnailCount")
	public val thumbnailCount: Int,
	/**
	 * The interval in milliseconds between each trickplay thumbnail.
	 */
	@SerialName("Interval")
	public val interval: Int,
	/**
	 * The peak bandwidth usage in bits per second.
	 */
	@SerialName("Bandwidth")
	public val bandwidth: Int,
)
