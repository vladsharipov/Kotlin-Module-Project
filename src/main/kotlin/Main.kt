fun main(args: Array<String>) {




    // Класс для управления приложением "Заметки"
    class NotesApp {
        private val archives = mutableListOf<Archive>()

        fun run() {
            while (true) {
                println("Выберите действие:")
                println("1. Выбрать архив")
                println("2. Создать архив")
                println("3. Выход")

                val choice = readLine()?.toIntOrNull()
                when (choice) {
                    1 -> selectArchive()
                    2 -> createArchive()
                    3 -> return
                    else -> println("Некорректный выбор. Попробуйте ещё раз.")
                }
            }
        }

        private fun selectArchive() {
            println("Список архивов:")
            println("0. Создать архив")
            for (i in archives.indices) {
                println("${i + 1}. ${archives[i].name}")
            }
            println("${archives.size + 1}. Выход")

            val choice = readLine()?.toIntOrNull()
            when {
                choice == 0 -> createArchive()
                choice in 1..archives.size -> {
                    val archive = archives[choice - 1]
                    viewArchive(archive)
                }
                choice == archives.size + 1 -> return
                else -> println("Некорректный выбор. Попробуйте ещё раз.")
            }
        }

        private fun createArchive() {
            println("Введите имя архива:")
            val name = readLine()?.trim()
            if (name.isNullOrBlank()) {
                println("Некорректное имя архива.")
            } else {
                val archive = Archive(name)
                archives.add(archive)
                println("Архив '$name' создан.")
            }
        }

        private fun viewArchive(archive: Archive) {
            while (true) {
                println("Выберите действие для архива '${archive.name}':")
                println("1. Добавить заметку")
                println("2. Просмотреть заметки")
                println("3. Вернуться к выбору архива")

                val choice = readLine()?.toIntOrNull()
                when (choice) {
                    1 -> createNote(archive)
                    2 -> viewNotes(archive)
                    3 -> return
                    else -> println("Некорректный выбор. Попробуйте ещё раз.")
                }
            }
        }

        private fun createNote(archive: Archive) {
            println("Введите заголовок заметки:")
            val title = readLine()?.trim()
            println("Введите содержимое заметки:")
            val content = readLine()?.trim()

            if (title.isNullOrBlank() || content.isNullOrBlank()) {
                println("Некорректные данные. Заметка не создана.")
            } else {
                val note = Note(title, content)
                archive.addNote(note)
                println("Заметка '$title' добавлена в архив '${archive.name}'.")
            }
        }

        private fun viewNotes(archive: Archive) {
            val notes = archive.getNotes()
            if (notes.isEmpty()) {
                println("Архив '${archive.name}' не содержит заметок.")
            } else {
                println("Заметки в архиве '${archive.name}':")
                for (i in notes.indices) {
                    val note = notes[i]
                    println("${i + 1}. ${note.title}")
                }
            }
        }
    }

    fun main() {
        val app = NotesApp()
        app.run()
    }

}