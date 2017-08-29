package com.vizurd.tutorial.repository

import com.vizurd.tutorial.entity.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository
import java.io.Serializable

@NoRepositoryBean
interface ReadOnlyRepository<T : Any?, ID : Serializable?> : org.springframework.data.repository.Repository<T, ID> {
    fun findOne(id: ID): T
    fun findAll(): Iterable<T>
}


@Repository
internal interface TranslationTypeRepo : ReadOnlyRepository<TranslationType, Int>

@Repository
internal interface LanguageRepo : ReadOnlyRepository<Language, Int>

@Repository
internal interface TranslationRepo : ReadOnlyRepository<Translation, String>

@Repository
internal interface CountryRepo : ReadOnlyRepository<Country, Int>

@Repository
internal interface DocumentTypeRepo : ReadOnlyRepository<DocumentType, Int>

@Repository
internal interface StatusRepo : ReadOnlyRepository<Status, Int>

@Repository
internal interface UserRepo : JpaRepository<User, Int>

@Repository
internal interface TutorialCategoryRepo : ReadOnlyRepository<TutorialCategory, Int>

@Repository
internal interface LessonRepo : JpaRepository<Lesson, Int>

@Repository
internal interface DocumentRepo : JpaRepository<Document, Int>

@Repository
internal interface DocumentHistoryRepo : JpaRepository<DocumentHistory, Int>

@Repository
internal interface LessonHistoryRepo : JpaRepository<LessonHistory, Int>
