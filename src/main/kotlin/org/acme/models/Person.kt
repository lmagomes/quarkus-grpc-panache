package org.acme.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity

@Entity
class Person: PanacheEntity() {

    companion object: PanacheCompanion<Person, Long> {}

    lateinit var name: String
}
