package com.SRB.noteudaao.timber

import timber.log.Timber

class DebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
    }
}
