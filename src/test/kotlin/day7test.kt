import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat

class Day7Test {

    @Test
    fun `Should return a single Pair of Int and String in a List when only one type of bag is passed in`() {

        val secondHalfOfBagString : String = "3 light yellow bags."
        val result = listOf<Pair<Int,String>>(Pair(3,"light yellow"))

        assertThat(convertSecondPartIntoBags(secondHalfOfBagString), equalTo(result))
    }

    @Test
    fun `Should return two Pair of Int and String in a List when two types of bag are passed in`() {

        val secondHalfOfBagString : String = "3 light yellow bags, 2 dim plum bags."
        val result = listOf(Pair(3,"light yellow"), Pair(2,"dim plum"))

        assertThat(convertSecondPartIntoBags(secondHalfOfBagString), equalTo(result))
    }

    @Test
    fun `Should return empty list when no other is passed in`() {

        val secondHalfOfBagString : String = "no other bags."
        val result = emptyList<Pair<Int,String>>()

        assertThat(convertSecondPartIntoBags(secondHalfOfBagString), equalTo(result))
    }

    @Test
    fun `Should return true when a colour is found in the inner bags for one inner bag`() {

        val innerBag  : List <Pair<Int, String>> = listOf(Pair(1, "yellow"))
        val colour = "yellow"


        assertThat(containsBagColour(colour,innerBag), equalTo(true))
    }

    @Test
    fun `Should return true when a colour is found in the inner bags for two inner bags`() {

        val innerBag  : List <Pair<Int, String>> = listOf(Pair(1, "yellow"), Pair(2,"pink"))
        val colour = "pink"


        assertThat(containsBagColour(colour,innerBag), equalTo(true))
    }

    @Test
    fun `Should return false when a colour is not  found in the inner bags`() {

        val innerBag  : List <Pair<Int, String>> = listOf(Pair(1, "yellow"))
        val colour = "red"


        assertThat(containsBagColour(colour,innerBag), equalTo(false))
    }

    @Test
    fun `Should return false when there is an empty list for the inner bags`() {

        val innerBag  : List <Pair<Int, String>> = emptyList()
        val colour = "red"


        assertThat(containsBagColour(colour,innerBag), equalTo(false))
    }
/*
    @Test
    fun `Should return true when the bag colour is found in the string to be checked`() {

        val bagString = "light red bags contain 1 bright white bag, 2 muted yellow bags."
        val bagColour = "bright white"

        assertThat(containsBagColour(bagString, bagColour), equalTo(true))
    }

    @Test
    fun `Should return false when the bag colour is not found in the string to be checked`() {

        val bagString = "light red bags contain 1 bright white bag, 2 muted yellow bags."
        val bagColour = "bogey green"

        assertThat(containsBagColour(bagString, bagColour), equalTo(false))
    }

    @Test
    fun `Should return false when the bag colour is only in the parent part of the string to be checked`() {

        val bagString = "light red bags contain 1 bright white bag, 2 muted yellow bags."
        val bagColour = "light red"

        assertThat(containsBagColour(bagString, bagColour), equalTo(false))
    }

    @Test
    fun `Should return the parent bag colour for the passed string`() {

        val bagString = "light red bags contain 1 bright white bag, 2 muted yellow bags."
        val bagColour = "light red"

        assertThat(parentBagColour(bagString), equalTo(bagColour))
    }

*/
}