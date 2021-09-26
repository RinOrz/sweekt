@file:Suppress("NOTHING_TO_INLINE")
@file:OptIn(ExperimentalContracts::class)

package com.meowool.sweekt.array

import com.meowool.sweekt.iteration.asList
import com.meowool.sweekt.iteration.size
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.jvm.JvmName

/**
 * Returns `true` if the array is not empty.
 */
inline fun <T> Array<T>?.isNotNullEmpty(): Boolean {
  contract {
    returns(true) implies (this@isNotNullEmpty != null)
  }
  return this != null && this.isNotEmpty()
}

/**
 * Call the given [action] when this array is not null and not empty.
 */
inline fun <T> Array<T>?.onNotNullEmpty(action: (Array<T>) -> Unit): Array<T>? {
  contract {
    returnsNotNull() implies (this@onNotNullEmpty != null)
    callsInPlace(action, InvocationKind.AT_MOST_ONCE)
  }
  if (this.isNotNullEmpty()) {
    action(this)
  }
  return this
}

/**
 * Call the given [action] when this array is not empty.
 */
inline fun <T> Array<T>.onNotEmpty(action: (Array<T>) -> Unit): Array<T> {
  contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }
  if (this.isNotEmpty()) {
    action(this)
  }
  return this
}

/**
 * Call the given [action] when this array is null or empty.
 */
inline fun <T> Array<T>?.onNullOrEmpty(action: (Array<T>?) -> Unit): Array<T>? {
  contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }
  if (this.isNullOrEmpty()) {
    action(this)
  }
  return this
}

/**
 * Call the given [action] when this array is empty.
 */
inline fun <T> Array<T>.onEmpty(action: (Array<T>) -> Unit): Array<T> {
  contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }
  if (this.isEmpty()) {
    action(this)
  }
  return this
}

/**
 * Returns itself if this [Array] is not empty, otherwise null.
 */
inline fun <T> Array<T>?.takeIfNotEmpty(): Array<T>? {
  contract {
    returnsNotNull() implies (this@takeIfNotEmpty != null)
  }
  return if (isNullOrEmpty()) null else this
}

/**
 * Returns itself if this [Array] is empty, otherwise null.
 */
inline fun <T> Array<T>.takeIfEmpty(): Array<T>? = if (isEmpty()) this else null

/**
 * Returns `true` if this array starts with given [slice].
 */
fun <T> Array<T>.startsWith(slice: Iterable<T>) = this.take(slice.size) == slice.asList()

/**
 * Returns `true` if this array ends with given [slice].
 */
fun <T> Array<T>.endsWith(slice: Iterable<T>) = this.takeLast(slice.size) == slice.asList()

/**
 * Returns `true` if this array starts with given [slice].
 */
fun <T> Array<T>.startsWith(slice: Sequence<T>) = this.take(slice.size) == slice.toList()

/**
 * Returns `true` if this array ends with given [slice].
 */
fun <T> Array<T>.endsWith(slice: Sequence<T>) = this.takeLast(slice.size) == slice.toList()

/**
 * Returns `true` if this array starts with given [slice].
 */
@JvmName("startsWithArray")
fun <T> Array<T>.startsWith(slice: Array<out T>) = this.take(slice.size) == slice.toList()

/**
 * Returns `true` if this array ends with given [slice].
 */
@JvmName("endsWithArray")
fun <T> Array<T>.endsWith(slice: Array<out T>) = this.takeLast(slice.size) == slice.toList()

/**
 * Returns `true` if this array starts with given [slice].
 */
fun <T> Array<T>.startsWith(vararg slice: T) = this.take(slice.size) == slice.toList()

/**
 * Returns `true` if this array ends with given [slice].
 */
fun <T> Array<T>.endsWith(vararg slice: T) = this.takeLast(slice.size) == slice.toList()

/**
 * Returns `true` if at least one element matches the given [predicate].
 */
inline fun <T> Array<T>.contains(predicate: (T) -> Boolean): Boolean = any(predicate)

/**
 * Returns `true` if at least one element matches the given [predicate].
 */
inline fun <T> Array<T>.has(predicate: (T) -> Boolean): Boolean = any(predicate)

/**
 * Drops the first element of this [Array] and returns a new list.
 */
inline fun <T> Array<T>.dropFirst(): List<T> = drop(1)

/**
 * Returns a list containing all elements except first [n] elements.
 *
 * @throws IllegalArgumentException if [n] is negative.
 */
inline fun <T> Array<T>.dropFirst(n: Int): List<T> = drop(n)

/**
 * Drops the last element of this [Iterable] and returns a new list.
 */
inline fun <T> Array<T>.dropLast(): List<T> = dropLast(1)

/**
 * If this array starts with the given [prefix], returns a list copy of this array with the prefix removed.
 * Otherwise, returns new copy list.
 */
fun <T> Array<T>.dropPrefix(prefix: T): List<T> = let {
  if (startsWith(prefix)) dropFirst() else it.toList()
}

/**
 * If this array starts with the given [prefix], returns a list copy of this array with the prefix removed.
 * Otherwise, returns new copy list.
 */
fun <T> Array<T>.dropPrefix(vararg prefix: T): List<T> = let {
  if (startsWith(*prefix)) dropFirst(prefix.size) else it.toList()
}

/**
 * If this array starts with the given [prefix], returns a list copy of this array with the prefix removed.
 * Otherwise, returns new copy list.
 */
@JvmName("dropPrefixArray")
fun <T> Array<T>.dropPrefix(prefix: Array<out T>): List<T> = let {
  if (startsWith(prefix)) dropFirst(prefix.size) else it.toList()
}

/**
 * If this array starts with the given [prefix], returns a list copy of this array with the prefix removed.
 * Otherwise, returns new copy list.
 */
fun <T> Array<T>.dropPrefix(prefix: Iterable<T>): List<T> = let {
  if (startsWith(prefix)) dropFirst(prefix.size) else it.toList()
}

/**
 * If this array ends with the given [suffix], returns a list copy of this array with the suffix removed.
 * Otherwise, returns new copy list.
 */
fun <T> Array<T>.dropSuffix(suffix: T): List<T> = let {
  if (endsWith(suffix)) dropLast() else it.toList()
}

/**
 * If this array ends with the given [suffix], returns a list copy of this array with the suffix removed.
 * Otherwise, returns new copy list.
 */
fun <T> Array<T>.dropSuffix(vararg suffix: T): List<T> = let {
  if (endsWith(*suffix)) dropLast(suffix.size) else it.toList()
}

/**
 * If this array ends with the given [suffix], returns a list copy of this array with the suffix removed.
 * Otherwise, returns new copy list.
 */
@JvmName("dropSuffixArray")
fun <T> Array<T>.dropSuffix(suffix: Array<out T>): List<T> = let {
  if (endsWith(suffix)) dropLast(suffix.size) else it.toList()
}

/**
 * If this array ends with the given [suffix], returns a list copy of this array with the suffix removed.
 * Otherwise, returns new copy list.
 */
fun <T> Array<T>.dropSuffix(suffix: Iterable<T>): List<T> = let {
  if (endsWith(suffix)) dropLast(suffix.size) else it.toList()
}