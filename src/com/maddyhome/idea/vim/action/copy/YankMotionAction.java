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

package com.maddyhome.idea.vim.action.copy;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.maddyhome.idea.vim.VimPlugin;
import com.maddyhome.idea.vim.command.Argument;
import com.maddyhome.idea.vim.command.Command;
import com.maddyhome.idea.vim.command.CommandFlags;
import com.maddyhome.idea.vim.command.MappingMode;
import com.maddyhome.idea.vim.handler.VimActionHandler;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;


public class YankMotionAction extends VimActionHandler.SingleExecution {
  @NotNull
  @Override
  public Set<MappingMode> getMappingModes() {
    return MappingMode.N;
  }

  @NotNull
  @Override
  public Set<List<KeyStroke>> getKeyStrokesSet() {
    return parseKeysSet("y");
  }

  @NotNull
  @Override
  public Command.Type getType() {
    return Command.Type.COPY;
  }

  @NotNull
  @Override
  public Argument.Type getArgumentType() {
    return Argument.Type.MOTION;
  }

  @NotNull
  @Override
  public EnumSet<CommandFlags> getFlags() {
    return EnumSet.of(CommandFlags.FLAG_OP_PEND);
  }

  @Override
  public boolean execute(@NotNull Editor editor, @NotNull DataContext context, @NotNull Command cmd) {
    final Argument argument = cmd.getArgument();
    if (argument == null) {
      return false;
    }

    return VimPlugin.getYank().yankMotion(editor, context, cmd.getCount(), cmd.getRawCount(), argument);
  }
}
