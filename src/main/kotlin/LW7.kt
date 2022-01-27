import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import java.io.File

class SerializableShapes {
    private val json = Json {
        prettyPrint = true

        serializersModule = SerializersModule {
            polymorphic(Shape::class) {
                subclass(Circle::class)
                subclass(Square::class)
                subclass(Rectangle::class)
                subclass(Triangle::class)
            }
        }
    }

    fun encode(shapes: List<Shape>): String {
        return json.encodeToString(shapes)
    }

    fun decode(str: String): MutableList<Shape> {
        return json.decodeFromString(str)
    }
}

class FileIO {
    fun write(filePath: String, toWrite: String) {
        File(filePath).writeText(toWrite)
    }

    fun read(filePath: String): String {
        return File(filePath).readText()
    }
}