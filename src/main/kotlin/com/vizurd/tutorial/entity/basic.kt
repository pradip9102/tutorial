package com.vizurd.tutorial.entity

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

private val UNDEFINED = "UNDEFINED"

@Entity
@Table(name = "translation_type", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("name"))))
internal data class TranslationType(
        @Id
        @Column(name = "id")
        val id: Int = 0,

        @Column(name = "name", length = 50, nullable = false, unique = true)
        val name: String = UNDEFINED,

        @Column(name = "description", length = 100)
        val description: String = UNDEFINED
) : Serializable


@Entity
@Table(name = "language", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("name"))))
internal data class Language(
        @Id
        @Column(name = "id")
        val id: Int = 0,

        @Column(name = "name", length = 50, nullable = false, unique = true)
        val name: String = "English (default)",

        @Column(name = "code", length = 2, nullable = false)
        val code: String = "en"
) : Serializable


@Entity
@Table(name = "translation", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("translation_type_id", "value"))))
internal data class Translation(
        @Id
        @Column(name = "key", length = 50)
        val key: String = UNDEFINED,

        @ManyToOne
        @JoinColumn(name = "translation_type_id")
        val translationType: TranslationType? = null,

        @ManyToOne
        @JoinColumn(name = "language_id", nullable = false)
        val language: Language,

        @Column(name = "value", nullable = false)
        val value: String = UNDEFINED
) : Serializable


@Entity
@Table(name = "country", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("name")), UniqueConstraint(columnNames = arrayOf("code"))))
internal data class Country(
        @Id
        @Column(name = "id")
        val id: Int = 0,

        @Column(name = "name", length = 50, nullable = false, unique = true)
        val name: String = UNDEFINED,

        @Column(name = "code", length = 3, nullable = false, unique = true)
        val code: String = UNDEFINED,

        @ManyToOne
        @JoinColumn(name = "language_id", nullable = false)
        val language: Language = Language(),

        @Column(name = "extension", length = 3, nullable = false)
        val extension: String = UNDEFINED
) : Serializable


@Entity
@Table(name = "document_type", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("type_name"))))
internal data class DocumentType(
        @Id
        @Column(name = "id")
        val id: Int = 0,

        @Column(name = "type_name", length = 100, nullable = false, unique = true)
        val typeName: String = UNDEFINED,

        @Column(name = "description", length = 100)
        val description: String = UNDEFINED
) : Serializable


@Entity
@Table(name = "status", uniqueConstraints = arrayOf())
internal data class Status(
        @Id
        @Column(name = "id")
        val id: Int = 0,

        @Column(name = "name", length = 20, nullable = false)
        val name: String = UNDEFINED,

        @Column(name = "description", length = 100)
        val description: String = UNDEFINED
) : Serializable


@Entity
@Table(name = "user", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("username", "password"))))
internal data class User(
        @Id
        @Column(name = "id")
        val id: Int = 0,

        @Column(name = "username", length = 100, nullable = false, unique = true)
        val username: String = UNDEFINED,

        @Column(name = "password", length = 50, nullable = false)
        val password: String = UNDEFINED,

        @Column(name = "first_name", length = 50)
        val firstName: String = UNDEFINED,

        @Column(name = "last_name", length = 50)
        val lastName: String = UNDEFINED,

        @Column(name = "dob")
        val dob: LocalDateTime = LocalDateTime.now(),

        @Column(name = "email", length = 50)
        val email: String = UNDEFINED,

        @Column(name = "mobile", length = 15)
        val mobile: String = UNDEFINED,

        @ManyToOne
        @JoinColumn(name = "country_id")
        val country: Country = Country(),

        @Column(name = "profession", length = 50)
        val profession: String = UNDEFINED,

        @ManyToOne
        @JoinColumn(name = "language_id")
        val language: Language = Language()
) : Serializable


@Entity
@Table(name = "tutorial_category", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("name"))))
internal data class TutorialCategory(
        @Id
        @Column(name = "id")
        val id: Int = 0,

        @Column(name = "name", length = 50, nullable = false, unique = true)
        val name: String = UNDEFINED,

        @Column(name = "description", length = 200)
        val description: String = UNDEFINED,

        @ManyToOne
        @JoinColumn(name = "parent_id")
        val parentTutorialCategory: TutorialCategory? = null
) : Serializable


@Entity
@Table(name = "lesson", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf())))
internal data class Lesson(
        @Id
        @Column(name = "id")
        val id: Int = 0,

        @Column(name = "name", length = 100, nullable = false)
        val name: String = UNDEFINED,

        @Column(name = "description", length = 500)
        val description: String = UNDEFINED,

        @ManyToOne
        @JoinColumn(name = "tutorial_category_id")
        val tutorialCategory: TutorialCategory = TutorialCategory()
) : Serializable

@Entity
@Table(name = "document", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf())))
internal data class Document(
        @Id
        @Column(name = "id")
        val id: Int = 0,

        @Column(name = "name", length = 100, nullable = false)
        val name: String = UNDEFINED,

        @ManyToOne
        @JoinColumn(name = "document_type_id")
        val documentType: DocumentType = DocumentType(),

        @Column(name = "path", length = 500)
        val path: String = "",

        @Column(name = "creation_date", nullable = false)
        val creationTime: LocalDateTime = LocalDateTime.now(),

        @Column(name = "modification_date", nullable = false)
        val modificationTime: LocalDateTime = LocalDateTime.now(),

        @ManyToOne
        @JoinColumn(name = "created_by_id", nullable = false)
        val createdBy: User = User(),

        @ManyToOne
        @JoinColumn(name = "modified_by_id", nullable = false)
        val modifiedBy: User = User()
) : Serializable

@Entity
@Table(name = "document_history", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf())))
internal data class DocumentHistory(
        @Id
        @Column(name = "id")
        val id: Int = 0,

        @ManyToOne
        @JoinColumn(name = "document_id")
        val document: Document = Document(),

        @ManyToOne
        @JoinColumn(name = "status_id")
        val status: Status = Status(),

        @ManyToOne
        @JoinColumn(name = "user_id")
        val user: User = User(),

        @Column(name = "comment", length = 100)
        val comment: String = ""
) : Serializable


@Entity
@Table(name = "lesson_history", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf())))
internal data class LessonHistory(
        @Id
        @Column(name = "id")
        val id: Int = 0,

        @ManyToOne
        @JoinColumn(name = "lesson_id")
        val lesson: Lesson = Lesson(),

        @ManyToOne
        @JoinColumn(name = "status_id")
        val status: Status = Status(),

        @ManyToOne
        @JoinColumn(name = "user_id")
        val user: User = User(),

        @Column(name = "comment", length = 100)
        val comment: String = ""
) : Serializable

