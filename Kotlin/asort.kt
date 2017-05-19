/*
thenByの中身について考察の余地あり
hashCode衝突しないかとか
オブジェクト同士を比較できる方法があれば良い
 */
fun <K : Any, V : Comparable<V>> MutableMap<K, V>.asort(): MutableMap<K, V>
        = this.toSortedMap(compareBy<K> { this[it] }.thenBy { it.hashCode() })

fun <K : Any, V : Comparable<V>> MutableMap<K, V>.arsort(): MutableMap<K, V>
        = this.toSortedMap(compareByDescending<K> { this[it] }.thenBy { it.hashCode() })