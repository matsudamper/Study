class MutablePairMap<K1, K2, V> {
    private val map: MutableMap<K1, MutableMap<K2, V>> = mutableMapOf()

    fun put(key1: K1, key2: K2, value: V): V? {
        val secondMap: MutableMap<K2, V> = map[key1] ?: mutableMapOf()
        val oldValue = secondMap.put(key2, value)

        map.put(key1, secondMap)

        return oldValue
    }

    fun get(key1: K1, key2: K2): V? = map.get(key1)?.get(key2)

    override fun toString(): String {
        val result = StringBuilder()

        for ((key1, secondMap) in map) {
            for ((key2, value) in secondMap) {
                result.append("$key1 : $key2 : $value").append("\n")
            }
        }

        return result.toString()
    }

    fun iterator(): Iterator<Triple<K1, K2, V>> = MutablePairMapIterator(map)

}

class MutablePairMapIterator<K1, K2, V>(pairMap: MutableMap<K1, MutableMap<K2, V>>) : Iterator<Triple<K1, K2, V>> {

    val firstIterator = pairMap.iterator()
    var firstMap: MutableMap.MutableEntry<K1, MutableMap<K2, V>>? = null

    var secondIterator: MutableIterator<MutableMap.MutableEntry<K2, V>>? = null

    init {
        if (firstIterator.hasNext()) {
            firstMap = firstIterator.next()
            secondIterator = firstMap!!.value.iterator()
        }
    }

    override fun hasNext(): Boolean {
        return firstIterator.hasNext() || secondIterator?.hasNext() ?: false
    }

    override fun next(): Triple<K1, K2, V> {

        if (!secondIterator!!.hasNext()) {
            firstMap = firstIterator.next()
            secondIterator = firstMap!!.value.iterator()
        }
        val secondMap = secondIterator!!.next()

        return Triple(firstMap!!.key, secondMap.key, secondMap.value)
    }
}