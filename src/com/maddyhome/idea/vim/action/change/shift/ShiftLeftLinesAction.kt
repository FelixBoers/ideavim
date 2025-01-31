/*
 * IdeaVim - Vim emulator for IDEs based on the IntelliJ platform
 * Copyright (C) 2003-2019 The IdeaVim authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.maddyhome.idea.vim.action.change.shift

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.maddyhome.idea.vim.VimPlugin
import com.maddyhome.idea.vim.command.Argument
import com.maddyhome.idea.vim.command.Command
import com.maddyhome.idea.vim.command.CommandFlags
import com.maddyhome.idea.vim.command.MappingMode
import com.maddyhome.idea.vim.handler.ChangeEditorActionHandler
import java.util.*
import javax.swing.KeyStroke


class ShiftLeftLinesAction : ChangeEditorActionHandler.ForEachCaret() {
  override val mappingModes: Set<MappingMode> = MappingMode.I

  override val keyStrokesSet: Set<List<KeyStroke>> = parseKeysSet("<C-D>")

  override val type: Command.Type = Command.Type.INSERT

  override val flags: EnumSet<CommandFlags> = EnumSet.of(CommandFlags.FLAG_SAVE_STROKE)

  override fun execute(editor: Editor,
                       caret: Caret,
                       context: DataContext,
                       count: Int,
                       rawCount: Int,
                       argument: Argument?): Boolean {
    VimPlugin.getChange().indentLines(editor, caret, context, count, -1)

    return true
  }
}

class ShiftLeftLinesNormalModeAction : ChangeEditorActionHandler.ForEachCaret() {
  override val mappingModes: Set<MappingMode> = MappingMode.N

  override val keyStrokesSet: Set<List<KeyStroke>> = parseKeysSet("<<")

  override val type: Command.Type = Command.Type.CHANGE

  override fun execute(editor: Editor,
                       caret: Caret,
                       context: DataContext,
                       count: Int,
                       rawCount: Int,
                       argument: Argument?): Boolean {
    VimPlugin.getChange().indentLines(editor, caret, context, count, -1)

    return true
  }
}
