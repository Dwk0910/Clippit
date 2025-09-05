[![Gradle CI](https://github.com/Dwk0910/Clippit/actions/workflows/gradle.yml/badge.svg)](https://github.com/Dwk0910/Clippit/actions/workflows/gradle.yml)
## *Clippit - A simple tool to clip, save, and load your directories or files*
Clippit is a simple CLI tool for managing and using **templates**. With Clippit, you can save, load, remove, and list templates easily from the command line.

### Installation
#### Homebrew
```
brew tap Dwk0910/Clippit
brew install clippit
```

### Usage
```
cl <COMMANDS/FLAGS>
```

example:<br/>
```
cl save template .
```

#### Flags
- `--v` or `--version`: Show installed Clippit version

#### Commands
**save**<br/>
```
cl save <TEMPLATE NAME> <TEMPLATE LOCATION>
```
Save a new template.
- `<TEMPLATE NAME>`: Name of the new template
- `<TEMPLATE LOCATION>`: Path to the original location of the template

**load**<br/>
```
cl load <TEMPLATE NAME> <TEMPLATE LOCATION (Optional)>
```
Load a saved template.
- `<TEMPLATE NAME>`: Name of the target template
- `<TEMPLATE LOCATION>`: Load Location (Optional, default: `.`)

**remove**<br/>
```
cl remove <TEMPLATE NAME>
```
Delete a template.
- `<TEMPLATE NAME>`: Name of the target template

**tree**
```
cl tree <TEMPLATE NAME>
```
Show the file tree of a template
- `<TEMPLATE NAME>`: Name of the target template

**list**
```
cl list
```
Show all saved templates.

**help**
```
cl help
```
Show help menu.

### LICENSE
This project is licensed under the `Apache 2.0 LICENSE` - see the [LICENSE](https://github.com/Dwk0910/Clippit/blob/master/LICENSE) for the details.
