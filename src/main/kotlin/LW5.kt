import java.time.Year

data class Book(
    val name: String,
    val author: Author,
    val genre: Genre,
    val year: Year
)

data class Author(val name: String)

data class User(val name: String)

enum class Genre {
    NOVEL,
    DETECTIVE,
    WESTERN,
    SCI_FI,
    THRILLER
}

sealed class Status {
    object Available : Status()
    data class UsedBy(val user: User) : Status()
    object ComingSoon : Status()
    object Restoration : Status()
}

interface LibraryService {
    fun findBooks(substring: String): List<Book>
    fun findBooks(author: Author): List<Book>
    fun findBooks(year: Year): List<Book>
    fun findBooks(genre: Genre): List<Book>

    fun getAllBooks(): List<Book>
    fun getAllAvailableBooks(): List<Book>

    fun getBookStatus(book: Book): Status?
    fun getAllBookStatuses(): Map<Book, Status>

    fun setBookStatus(book: Book, status: Status)

    fun addBook(book: Book, status: Status = Status.Available)

    fun registerUser(user: User)
    fun unregisterUser(user: User)

    fun takeBook(user: User, book: Book)
    fun returnBook(book: Book)
}

class LibraryServiceImpl : LibraryService {
    private val listOfBooks = mutableMapOf<Book, Status>()
    private val listOfUsers = mutableSetOf<User>()

    override fun findBooks(substring: String): List<Book> {
        val subList = mutableListOf<Book>()
        for (i in listOfBooks) {
            if (i.key.name == substring)
                subList.add(i.key)
        }
        return subList
    }

    override fun findBooks(author: Author): List<Book> {
        val subList = mutableListOf<Book>()
        for (i in listOfBooks) {
            if (i.key.author == author)
                subList.add(i.key)
        }
        return subList
    }

    override fun findBooks(year: Year): List<Book> {
        val subList = mutableListOf<Book>()
        for (i in listOfBooks) {
            if (i.key.year == year)
                subList.add(i.key)
        }
        return subList
    }

    override fun findBooks(genre: Genre): List<Book> {
        val subList = mutableListOf<Book>()
        for (i in listOfBooks) {
            if (i.key.genre == genre)
                subList.add(i.key)
        }
        return subList
    }

    override fun getAllBooks(): List<Book> {
        return listOfBooks.map { it.key }
    }

    override fun getAllAvailableBooks(): List<Book> {
        val subList = mutableListOf<Book>()
        for (i in listOfBooks) {
            if (i.value == Status.Available)
                subList.add(i.key)
        }
        return subList
    }

    override fun getBookStatus(book: Book): Status? {
        for (i in listOfBooks) {
            if (i.key == book)
                return i.value
        }
        return null
    }


    override fun getAllBookStatuses(): Map<Book, Status> {
        return listOfBooks.toMap()
    }

    override fun setBookStatus(book: Book, status: Status) {
        if (!listOfBooks.contains(book))
            throw IllegalArgumentException("There's no such book in a library.")
        if (listOfBooks[book] == status)
            throw IllegalArgumentException("This book is already having that status.")
        listOfBooks[book] = status
    }

    override fun addBook(book: Book, status: Status) {
        listOfBooks[book] = status
    }

    override fun registerUser(user: User) {
        if (listOfUsers.contains(user))
            throw IllegalArgumentException("This user has already been registered.")
        listOfUsers.add(user)
    }

    override fun unregisterUser(user: User) {
        if (listOfUsers.contains(user))
            throw IllegalArgumentException("There's no such user registered in library.")
        for (i in listOfBooks) {
            if (i.value == Status.UsedBy(user))
                setBookStatus(i.key, Status.Available)
        }
        listOfUsers.remove(user)
    }

    override fun takeBook(user: User, book: Book) {
        if (!listOfBooks.contains(book))
            throw IllegalArgumentException("There's no such book in a library.")
        if (!listOfUsers.contains(user))
            throw IllegalArgumentException("There's no such user registered in library.")
        if (computeUsersBooks(user) >= 3)
            throw IllegalArgumentException("Users aren't allowed to take more than 3 books.")
        setBookStatus(book, Status.UsedBy(user))
    }

    private fun computeUsersBooks(user: User): Int {
        var result = 0
        for (i in listOfBooks) {
            if (i.value == Status.UsedBy(user))
                result++
        }
        return result
    }

    override fun returnBook(book: Book) {
        setBookStatus(book, Status.Available)
    }
}