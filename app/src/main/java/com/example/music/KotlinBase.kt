package com.example.music

import java.util.*
import kotlin.math.max


fun main() {
    val ans = longestPalindrome("babad")
    println(ans)
}

/**
 * One
 */
fun twoSum(nums: IntArray, target: Int): IntArray {
    val ans = IntArray(2)
    val map = mutableMapOf<Int, Int>()
    nums.forEachIndexed() { index, num ->
        val anotherIndex = map[target - num]
        anotherIndex?.let {
            ans[0] = index
            ans[1] = anotherIndex
            return ans
        }
        map[num] = index
    }
    return ans
}

/**
 * 链表结点
 */
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

/**
 * Two
 */
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    val ans = ListNode(0)
    var cur = ans
    var carry = 0
    var l1Cur = l1
    var l2Cur = l2
    while (l1Cur != null || l2Cur != null) {
        val n1 = l1Cur?.`val` ?: 0
        val n2 = l2Cur?.`val` ?: 0
        val num = n1 + n2 + carry
        carry = num / 10
        val digit = num % 10
        cur.next = ListNode(digit)
        cur = cur.next ?: cur
        l1Cur = l1Cur?.next
        l2Cur = l2Cur?.next
    }
    cur.next = if (carry == 1) ListNode(1) else null
    return ans.next
}

/**
 * Three
 */
fun lengthOfLongestSubstring(s: String): Int {
    var maxLen = 0
    val mapOfThree = mutableMapOf<Char, Int>()
    var lastRepeat = -1
    s.forEachIndexed { i, c ->
        val beforeC = mapOfThree[c]
        lastRepeat = max(beforeC ?: -1, lastRepeat)
        mapOfThree[c] = i
        maxLen = max(maxLen, i - lastRepeat)
    }
    return maxLen
}

/**
 * Four
 */
fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    return 2.0
}

/**
 * Five
 */
fun longestPalindrome(s: String): String {
    fun maxFromCurChar(s: String, left: Int, right: Int): Int {
        var res = 0
        var l = left
        var r = right
        while (l >= 0 && r < s.length && s[l] == s[r]) {
            res++
            l--
            r++
        }
        return r - l - 1
    }

    var ans = 0
    var beginCharIndex = 0
    for (i in 0 until s.length) {
        val len1 = maxFromCurChar(s, i, i)
        val len2 = maxFromCurChar(s, i - 1, i)
        val len = max(len1, len2)
        if (len > ans) {
            ans = len
            beginCharIndex = i - len / 2
        }
    }

    return String(s.toCharArray(), beginCharIndex, ans)
}


