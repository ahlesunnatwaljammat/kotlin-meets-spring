package edu.learn.test.redis.activity

import edu.learn.config.main.MainConfig
import edu.learn.redis.entities.Activity
import edu.learn.redis.repos.ActivityRepo
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@ActiveProfiles(value = ["test"])
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [MainConfig::class])
@EnableRedisRepositories(basePackages = ["edu.learn.redis.repos"])
@EnableAutoConfiguration
class ActivityTest {
    @Autowired
    lateinit var activityRepo : ActivityRepo

    @Test
    fun pass_1() {
        this.activityRepo.save(Activity("nabbasi","Logged In"))
    }

    @Test
    fun pass_2() {
        val activity = this.activityRepo.findById("nabbasi").get()
        Assert.assertNotNull(activity)
    }
}