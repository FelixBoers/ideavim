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

package com.maddyhome.idea.vim.key;

import com.maddyhome.idea.vim.command.Command;
import com.maddyhome.idea.vim.command.CommandFlags;
import com.maddyhome.idea.vim.handler.EditorActionHandlerBase;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.EnumSet;
import java.util.Objects;

/**
 * This node represents a command in the key/action tree
 */
public class CommandNode implements Node {
  /**
   * Creates a command node for the key and action
   *
   * @param key     The final keystroke in this command
   * @param action  The action that executes this command
   * @param cmdType The type of the command
   * @param flags   Any special flags needs by the command
   */
  @Contract(pure = true)
  public CommandNode(KeyStroke key, EditorActionHandlerBase action, @NotNull Command.Type cmdType, EnumSet<CommandFlags> flags) {
    this.key = key;
    this.action = action;
    this.type = cmdType;
    this.flags = flags;
  }

  /**
   * Gets the command's action
   *
   * @return The command's action
   */
  public EditorActionHandlerBase getAction() {
    return action;
  }

  /**
   * Gets the command's keystroke
   *
   * @return The command's keystroke
   */
  public KeyStroke getKey() {
    return key;
  }

  /**
   * Gets the command's type
   *
   * @return The command's type
   */
  @NotNull
  public Command.Type getCmdType() {
    return type;
  }

  /**
   * Gets the command's flags
   *
   * @return The command's flags
   */
  public EnumSet<CommandFlags> getFlags() {
    return flags;
  }

  @NotNull
  public String toString() {

    return "CommandNode[key=" + key + ", action=" + action + ", argType=" + type + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CommandNode that = (CommandNode) o;
    return Objects.equals(key, that.key) &&
            Objects.equals(action, that.action) &&
            type == that.type &&
            Objects.equals(flags, that.flags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, action, type, flags);
  }

  protected final KeyStroke key;
  protected final EditorActionHandlerBase action;
  @NotNull protected final Command.Type type;
  protected final EnumSet<CommandFlags> flags;
}
