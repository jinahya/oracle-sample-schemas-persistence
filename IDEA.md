# IntelliJ IDEA

## Replace getters and setters for Builders

### Replace `<TYPE> getXxxYyy` into `<TYPE> xxxYyy`

* `public (?<type>\w+) get(?<name>\w+)`
* `public ${type} ${name}`
    * with ***Preserve case*** on

### Replace `void setXxxYyy` into `SELF xxxYyy`

* `public void set(?<name>\w+)`
* `public SELF \l$1`

### Append `return (SELF) this;` to `this.xxxYyy = xxxYyy;`

* `this.\w+ = \w+;`
* `$0\n        return (SELF) this;`
