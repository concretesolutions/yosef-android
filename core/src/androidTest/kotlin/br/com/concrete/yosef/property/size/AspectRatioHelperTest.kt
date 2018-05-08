package br.com.concrete.yosef.property.size

import android.support.test.runner.AndroidJUnit4
import br.com.concrete.yosef.api.property.size.AspectRatioHelper
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AspectRatioHelperTest {

    private lateinit var aspectRatioHelper: AspectRatioHelper

    @Before
    fun setUp() {
        aspectRatioHelper = AspectRatioHelper()
    }

    @Test
    fun whenValueSplitWithWrongCharacter_itCannotBeParsed() {
        val invalidValue = "16,9"

        val valueCanBeParsed = aspectRatioHelper.valueCanBeParsed(invalidValue)

        assertFalse(valueCanBeParsed)
    }

    @Test
    fun whenValueIsMissingInformation_itCannotBeParsed() {
        val invalidValue = "16:"

        val valueCanBeParsed = aspectRatioHelper.valueCanBeParsed(invalidValue)

        assertFalse(valueCanBeParsed)
    }

    @Test
    fun whenValueIsEmpty_itCannotBeParsed() {
        val invalidValue = ""

        val valueCanBeParsed = aspectRatioHelper.valueCanBeParsed(invalidValue)

        assertFalse(valueCanBeParsed)
    }

    @Test
    fun whenValueSplicedLengthIsGreaterThanTwo_itCannotBeParsed() {
        val invalidValue = "16:16:9"

        val valueCanBeParsed = aspectRatioHelper.valueCanBeParsed(invalidValue)

        assertFalse(valueCanBeParsed)
    }

    @Test
    fun whenValueWidthIsNotAnInteger_itCannotBeParsed() {
        val invalidValue = "WIDTH:9"

        val valueCanBeParsed = aspectRatioHelper.valueCanBeParsed(invalidValue)

        assertFalse(valueCanBeParsed)
    }

    @Test
    fun whenValueHeightIsNotAnInteger_itCannotBeParsed() {
        val invalidValue = "16:HEIGHT"

        val valueCanBeParsed = aspectRatioHelper.valueCanBeParsed(invalidValue)

        assertFalse(valueCanBeParsed)
    }

    @Test
    fun whenValidValue_itCanBeParsed() {
        val validValue = "16:9"

        val valueCanBeParsed = aspectRatioHelper.valueCanBeParsed(validValue)

        assertTrue(valueCanBeParsed)
    }

    @Test
    fun whenValidValue_shouldGenerateCorrectHeight() {
        val validValue = "16:9"

        val generatedHeight = aspectRatioHelper.generateHeightGivenAspectRatio(validValue, 1280)

        assertEquals(generatedHeight, 720)
    }
}
