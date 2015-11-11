/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.sishuok.es.tmp.parentchild;

import com.sishuok.es.common.test.BaseIT;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-5-8 下午3:01
 * <p>Version: 1.0
 */
public class ParentChildTest extends BaseIT {

    @Test
    public void testCascade() {
        Parent p = new Parent();
        p.setId("123");

        Child c1 = new Child();
        c1.setId("1");
        c1.setName("123");
        p.addChild(c1);

        Child c2 = new Child();
        c2.setId("2");
        c2.setName("234");
        p.addChild(c2);

        entityManager.persist(p);
        entityManager.flush();
        entityManager.clear();
        entityManager.close();

        Parent persistParent = entityManager.find(Parent.class, "123");
        Assert.isTrue(2 == persistParent.getChilds().size());
        

        p = new Parent();
        p.setId("123");

        c2 = new Child();
        c2.setId("2");
        c2.setName("345");
        p.addChild(c2);

        Child c3 = new Child();
        c3.setId("23");
        c3.setName("456");
        p.addChild(c3);

        entityManager.merge(p);

        entityManager.flush();
        entityManager.clear();
        
        persistParent = entityManager.find(Parent.class, "123");
        Assert.isTrue(2 == persistParent.getChilds().size());
        for(Child child:persistParent.getChilds()){
        	System.out.println(child.getName());
        }

        p = entityManager.find(Parent.class, "123");

    }

}
