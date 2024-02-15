package domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Beer(
    val id: Long,
    val name: String,
    val tagline: String,
    @SerialName("first_brewed")
    val firstBrewed: String,
    val description: String,
    @SerialName("image_url")
    val imageUrl: String?
)
