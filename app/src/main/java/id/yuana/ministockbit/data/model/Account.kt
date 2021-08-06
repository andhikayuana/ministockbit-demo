package id.yuana.ministockbit.data.model

data class Account(
    val name: String,
    val email: String,
    val avatar: String
) {

    companion object {

        const val DUMMY_PASSWORD = "jarjit123!@#"

        fun createDummy(): Account = Account(
            name = "Jarjit Singh",
            email = "jarjit@spam4.me",
            avatar = "https://ui-avatars.com/api/?name=Jarjit+Singh"
        )

        fun createPlaceholder(): Account =
            Account(name = "Loading...", email = "Loading...", avatar = "Loading...")
    }
}