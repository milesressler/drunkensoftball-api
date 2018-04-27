package com.drunkensoftball.api.domain


import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.*
import java.util.Calendar
import java.util.UUID

@MappedSuperclass
abstract class BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "uuid")
    var uuid: String? = null

    @Column(name = "created", updatable = false)
    var created: Calendar? = null

    @Column(name = "updated")
    var updated: Calendar? = null

    @JsonIgnore
    @Column(name = "removed", nullable = false)
    var removed: Boolean = false

    @PrePersist
    protected fun onCreate() {
        this.created = Calendar.getInstance()
        this.uuid = UUID.randomUUID().toString()
    }

    @PreUpdate
    protected fun onUpdate() {
        this.updated = Calendar.getInstance()
    }
}
