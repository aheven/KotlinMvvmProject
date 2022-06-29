package heven.holt.library.track

class TrackParams {
    private val map = mutableMapOf<String, String>()

    fun put(key: String, value: Any?): TrackParams = apply { map[key] = value.toString() }

    fun putAll(params: Map<String, String>): TrackParams = apply { map.putAll(params) }

    fun get(key: String): String? = map[key]

    fun toMap(): Map<String, String> = map

    override fun toString(): String = map.toString()
}