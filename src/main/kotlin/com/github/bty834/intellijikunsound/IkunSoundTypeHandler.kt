package com.github.bty834.intellijikunsound

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import javazoom.jl.player.Player
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy
import java.util.concurrent.TimeUnit

val playTP = ThreadPoolExecutor(1,1,10, TimeUnit.MINUTES, ArrayBlockingQueue(10), DiscardPolicy())
class IkunSoundTypeHandler : TypedHandlerDelegate() {

    override fun charTyped(c: Char, project: Project, editor: Editor, file: PsiFile): Result {
        if (c.lowercaseChar() == 'j') {
            playTP.execute{playVoice('j')}
        }
        return super.charTyped(c, project, editor, file)
    }
}

private fun playVoice(c: Char) {
    val mp3Stream = IkunSoundTypeHandler::class.java.getResourceAsStream("/ikun/j.mp3")
    val player = Player(mp3Stream)
    player.play()
    player.close()
}