import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat



class Day3Test{
    val testForestMap = listOf(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#"
    )

    @Test
    fun `Should show there is a tree at the location specified`() {
        val location = Pair(3,0)
        assertThat(isTreeByLocation(testForestMap,location), equalTo(true))
    }
    @Test
    fun `Should show there is not a tree at the location specified`() {
        val location = Pair(1,1)
        assertThat(isTreeByLocation(testForestMap,location), equalTo(false))
    }

    @Test
    fun `Should show there is a tree at the location specified outside of map size`() {
        val location = Pair(14,0)
        assertThat(isTreeByLocation(testForestMap,location), equalTo(true))
    }

    @Test
    fun `Should show there is a tree at the location specified outside of mapsize`() {
        val location = Pair(12,0)
        assertThat(isTreeByLocation(testForestMap,location), equalTo(false))
    }

    @Test
    fun `Should generate a route list`() {
        val slope =  Pair(1,1)
        val slopeLength = 3
        assertThat(generateRoute(slope, slopeLength), equalTo(listOf(Pair(1,1),Pair(2,2))))
    }

    @Test
    fun `Should calculate product of trees hit`() {
        val slopeList =  listOf(Pair(3,1))
        assertThat(calculateTreesHitProduct(testForestMap,slopeList), equalTo(7))
    }

    @Test
    fun `Should calculate product of trees hit, 2 slopes`() {
        val slopeList =  listOf(Pair(3,1), Pair(1,1))
        assertThat(calculateTreesHitProduct(testForestMap,slopeList), equalTo(14))
    }

}