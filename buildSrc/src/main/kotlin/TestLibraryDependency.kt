private object TestLibraryVersion {
    const val JUNIT = "4.13"
    const val MOCKITO = "3.5.2"
    const val MOCKITO_KOTLIN = "2.1.0"
}

object TestLibraryDependency {
    const val JUNIT = "junit:junit:${TestLibraryVersion.JUNIT}"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:${TestLibraryVersion.MOCKITO}"
    const val MOCKITO_KOTLIN = "com.nhaarman.mockitokotlin2:mockito-kotlin:${TestLibraryVersion.MOCKITO_KOTLIN}"

//    const val MOCKK = "io.mockk:mockk:1.9.3"
//
//    const val POWER_MOCK_JUNIT = "org.powermock:powermock-module-junit4:2.0.4"
//    const val POWER_MOCK_MOCKITO2 = "org.powermock:powermock-api-mockito2:2.0.4"
}
