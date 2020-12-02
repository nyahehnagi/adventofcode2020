import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat

class Day2Test {

    @Test
    fun `Should be a valid password`() {
        val policy = PasswordPolicy (1,1,'a')
        val password = "a"

        assertThat(policy.isValid(password), equalTo(true))
    }
    @Test
    fun `Should be an invalid password`() {
        val policy = PasswordPolicy (1,1,'a')
        val password = "b"

        assertThat(policy.isValid(password), equalTo(false))
    }

    @Test
    fun `Should be a valid password for official policy`() {
        val policy = PasswordPolicy (1,3,'a')
        val password = "abcde"

        assertThat(policy.isOfficialTobagganPolicyValid(password), equalTo(true))
    }

    @Test
    fun `Should be an invalid password for official policy - 2 occurances`() {
        val policy = PasswordPolicy (2,9,'c')
        val password = "ccccccccc"
        assertThat(policy.isOfficialTobagganPolicyValid(password), equalTo(false))
    }
    @Test

    fun `Should be an invalid password for official policy - no occurances`() {
        val policy = PasswordPolicy (1,3,'b')
        val password = "cdefg"

        assertThat(policy.isOfficialTobagganPolicyValid(password), equalTo(false))
    }


}