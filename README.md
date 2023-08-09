# ExplosionsControl
--------------------------------------------------------------------------
Control the explosions in your Minecraft server! Configurations are separate for each world, custom worlds supporrted!

## Commands
```yaml
commands:
  updateworlds:
    description: Update all world explosion configs
    usage: /updateworlds
    permission: exc.commands.updateworlds
  configworld:
    description: Config world explosion configs
    usage: /configworld <world>
    permission: exc.commands.configworld
```

## Permissions
```yml
permissions:
  exc.commands.updateworlds:
    description: Command access
    default: op
  exc.commands.configworlds:
    description: Command access
    default: op
```