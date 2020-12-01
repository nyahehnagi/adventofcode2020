import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat

class Day1Test {

    @Test
    fun `Should return a matched pair for the given input`() {
        val intList = listOf(1, 3, 5)
        val sumValue = 4

        assertThat(findTwoEntriesThatSumToValue(intList, sumValue), equalTo(Pair(1,3)))
    }

    @Test
    fun `Should return a matched triple for the given input`() {
        val intList = listOf(1721, 979 , 366, 299, 675, 1456)
        val sumValue = 2020
        assertThat(findThreeEntriesThatSumToValue(intList, sumValue), equalTo(Triple(979,366, 675)))
    }


}