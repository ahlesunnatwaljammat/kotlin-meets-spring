package edu.learn.test.jpa.user

import edu.learn.jpa.repos.UserRepo
import edu.learn.config.main.MainConfig
import edu.learn.jpa.entities.User
import  org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@ActiveProfiles(value = ["test"])
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [MainConfig::class])
class UserTest {
    @Autowired
    lateinit var userRepo: UserRepo

    companion object {
        fun username() : String{
            return "nabbasi"
        }
    }

    @Test
    fun pass_1(){
        val user = this.userRepo.save(User(username = UserTest.username(), password = "x"))
        assertEquals(UserTest.username(), user.username)
        assertEquals("x", user.password)
    }

    @Test(expected = DataIntegrityViolationException::class)
    fun pass_2(){
        this.userRepo.save(User(username = UserTest.username(), password = "x1"))
    }

    @Test
    fun pass_3(){
        val users = this.userRepo.findAll()
        assertTrue(users.count() == 4)
    }

    @Test(expected = EmptyResultDataAccessException::class)
    fun pass_x(){
        val user = this.userRepo.findByUsername(UserTest.username())
        this.userRepo.delete(user)
        this.userRepo.findByUsername(UserTest.username())
    }
}