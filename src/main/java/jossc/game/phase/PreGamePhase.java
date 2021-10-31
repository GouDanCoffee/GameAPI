package jossc.game.phase;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.math.Vector3;
import cn.nukkit.plugin.PluginBase;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jossc.game.utils.math.MathUtils;

public class PreGamePhase extends GamePhase {

  private int countdown;

  public PreGamePhase(PluginBase plugin, int countdown) {
    super(plugin, Duration.ZERO);
    this.countdown = countdown;
  }

  @Override
  protected void onStart() {
    spawnPlayers();
  }

  private void spawnPlayers() {
    List<Vector3> spawns = game.getSpawns();
    Set<Integer> spawnsUsed = new HashSet<>();

    getNeutralPlayers()
      .forEach(player -> spawnPlayer(player, spawns, spawnsUsed));
  }

  private void spawnPlayer(
    Player player,
    List<Vector3> spawns,
    Set<Integer> spawnsUsed
  ) {
    player.setGamemode(game.getDefaultGameMode());

    int i;

    do {
      i = MathUtils.nextInt(spawns.size());
    } while (spawnsUsed.contains(i));

    player.teleport(spawns.get(i));
    spawnsUsed.add(i);
  }

  @Override
  public void onUpdate() {
    if (countdown > 0) {
      countdown--;

      if (countdown < 3) {
        broadcastSound("");
      }

      broadcastActionBar("&eThe game starts in &l" + countdown);

      return;
    }

    if (countdown == 0) {
      String instruction = game.getInstruction();

      if (!instruction.isEmpty()) {
        broadcastMessage("&7&l» &r&f" + instruction);
      }

      List<String> tips = game.getTips();

      if (tips == null || tips.isEmpty()) {
        return;
      }

      int index = MathUtils.nextInt(0, tips.size());
      String tip = tips.get(index);

      if (tip == null) {
        return;
      }

      broadcastMessage("&b&l» &r&bTip: &7" + tip);
      broadcastSound("random.toast");
    }
  }

  @Override
  public boolean isReadyToEnd() {
    return super.isReadyToEnd() && countdown == 0;
  }

  @EventHandler
  public void onMove(PlayerMoveEvent event) {
    event.setTo(event.getFrom());
  }
}
