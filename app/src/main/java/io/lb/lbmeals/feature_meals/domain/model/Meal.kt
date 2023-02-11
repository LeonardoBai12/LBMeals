package io.lb.lbmeals.feature_meals.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Meal data.
 *
 * @property id Meal API ID.
 * @property name Meal name.
 * @property category Meal category name.
 * @property instructions Instructions to cook the meal.
 * @property area Original country of the meal.
 * @property youTubeUrl YouTube video URL of the meal.
 * @property thumbnail Meal thumbnail URL.
 * @property ingredient1 1st ingredient.
 * @property measure1 Measure of the 1st ingredient.
 * @property ingredient2 2nd ingredient.
 * @property measure2 Measure of the 2nd ingredient.
 * @property ingredient3 3rd ingredient.
 * @property measure3 Measure of the 3rd ingredient.
 * @property ingredient4 4th ingredient.
 * @property measure4 Measure of the 4th ingredient.
 * @property ingredient5 5th ingredient.
 * @property measure5 Measure of the 5th ingredient.
 * @property ingredient6 6th ingredient.
 * @property measure6 Measure of the 6th ingredient.
 * @property ingredient7 7th ingredient.
 * @property measure7 Measure of the 7th ingredient.
 * @property ingredient8 8th ingredient.
 * @property measure8 Measure of the 8th ingredient.
 * @property ingredient9 9th ingredient.
 * @property measure9 Measure of the 9th ingredient.
 * @property ingredient10 10th ingredient.
 * @property measure10 Measure of the 10th ingredient.
 * @property ingredient11 11th ingredient.
 * @property measure11 Measure of the 11th ingredient.
 * @property ingredient12 12th ingredient.
 * @property measure12 Measure of the 12th ingredient.
 * @property ingredient13 13th ingredient.
 * @property measure13 Measure of the 13th ingredient.
 * @property ingredient14 14th ingredient.
 * @property measure14 Measure of the 14th ingredient.
 * @property ingredient15 15th ingredient.
 * @property measure15 Measure of the 15th ingredient.
 * @property ingredient16 16th ingredient.
 * @property measure16 Measure of the 16th ingredient.
 * @property ingredient17 17th ingredient.
 * @property measure17 Measure of the 17th ingredient.
 * @property ingredient18 18th ingredient.
 * @property measure18 Measure of the 18th ingredient.
 * @property ingredient19 19th ingredient.
 * @property measure19 Measure of the 189th ingredient.
 * @property ingredient20 20th ingredient.
 * @property measure20 Measure of the 20th ingredient.
 */
data class Meal(
    @SerializedName("idMeal")
    val id: String,
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strCategory")
    val category: String,
    @SerializedName("strArea")
    val area: String?,
    @SerializedName("strInstructions")
    val instructions: String,
    @SerializedName("strMealThumb")
    val thumbnail: String,
    @SerializedName("strYoutube")
    val youTubeUrl: String?,
    @SerializedName("strIngredient1")
    val ingredient1: String?,
    @SerializedName("strIngredient2")
    val ingredient2: String?,
    @SerializedName("strIngredient3")
    val ingredient3: String?,
    @SerializedName("strIngredient4")
    val ingredient4: String?,
    @SerializedName("strIngredient5")
    val ingredient5: String?,
    @SerializedName("strIngredient6")
    val ingredient6: String?,
    @SerializedName("strIngredient7")
    val ingredient7: String?,
    @SerializedName("strIngredient8")
    val ingredient8: String?,
    @SerializedName("strIngredient9")
    val ingredient9: String?,
    @SerializedName("strIngredient10")
    val ingredient10: String?,
    @SerializedName("strIngredient11")
    val ingredient11: String?,
    @SerializedName("strIngredient12")
    val ingredient12: String?,
    @SerializedName("strIngredient13")
    val ingredient13: String?,
    @SerializedName("strIngredient14")
    val ingredient14: String?,
    @SerializedName("strIngredient15")
    val ingredient15: String?,
    @SerializedName("strIngredient16")
    val ingredient16: String?,
    @SerializedName("strIngredient17")
    val ingredient17: String?,
    @SerializedName("strIngredient18")
    val ingredient18: String?,
    @SerializedName("strIngredient19")
    val ingredient19: String?,
    @SerializedName("strIngredient20")
    val ingredient20: String?,
    @SerializedName("strMeasure1")
    val measure1: String?,
    @SerializedName("strMeasure2")
    val measure2: String?,
    @SerializedName("strMeasure3")
    val measure3: String?,
    @SerializedName("strMeasure4")
    val measure4: String?,
    @SerializedName("strMeasure5")
    val measure5: String?,
    @SerializedName("strMeasure6")
    val measure6: String?,
    @SerializedName("strMeasure7")
    val measure7: String?,
    @SerializedName("strMeasure8")
    val measure8: String?,
    @SerializedName("strMeasure9")
    val measure9: String?,
    @SerializedName("strMeasure10")
    val measure10: String?,
    @SerializedName("strMeasure11")
    val measure11: String?,
    @SerializedName("strMeasure12")
    val measure12: String?,
    @SerializedName("strMeasure13")
    val measure13: String?,
    @SerializedName("strMeasure14")
    val measure14: String?,
    @SerializedName("strMeasure15")
    val measure15: String?,
    @SerializedName("strMeasure16")
    val measure16: String?,
    @SerializedName("strMeasure17")
    val measure17: String?,
    @SerializedName("strMeasure18")
    val measure18: String?,
    @SerializedName("strMeasure19")
    val measure19: String?,
    @SerializedName("strMeasure20")
    val measure20: String?,
)
