/*
 * Copyright 2021-2055 Josscoder
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package net.josscoder.gameapi.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import net.josscoder.gameapi.Game;

public abstract class GameCommand extends Command {

  protected final Game game;

  public GameCommand(Game game, String name) {
    super(name);
    this.game = game;
  }

  public GameCommand(Game game, String name, String description) {
    super(name, description);
    this.game = game;
  }

  public GameCommand(
    Game game,
    String name,
    String description,
    String usageMessage
  ) {
    super(name, description, usageMessage);
    this.game = game;
  }

  public GameCommand(
    Game game,
    String name,
    String description,
    String usageMessage,
    String[] aliases
  ) {
    super(name, description, usageMessage, aliases);
    this.game = game;
  }

  @Override
  public boolean execute(CommandSender sender, String label, String[] args) {
    return false;
  }
}
