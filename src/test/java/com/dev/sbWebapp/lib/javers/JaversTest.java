package com.dev.sbWebapp.lib.javers;

import org.javers.core.Javers;
import org.javers.core.diff.Diff;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
public class JaversTest {

    @Autowired
    private Javers javers;

    @Test
    void javers_commi(){
        Person oldPerson = new Person("p1", "Alice", 20);
        Person newPerson = new Person("p1", "Alice", 25);

        // 커밋 (DB에 저장됨)
        javers.commit("user1", oldPerson);
        javers.commit("user1", newPerson);

        // Diff 비교
        Diff diff = javers.compare(oldPerson, newPerson);
        System.out.println(diff.prettyPrint());
    }
}
