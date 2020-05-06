package com.extendedclip.expansion.defaultvalue;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public final class DefaultValueExpansion extends PlaceholderExpansion {

  private final String VERSION = getClass().getPackage().getImplementationVersion();

  @Override
  public String getIdentifier() {
    return "defaultvalue";
  }

  @Override
  public String getAuthor() {
    return "clip";
  }

  @Override
  public String getVersion() {
    return VERSION;
  }

  @Override
  public String onRequest(OfflinePlayer player, String args) {

    // %defaultvalue_{placeholder}-type-def%

    String[] parts = args.split("-");

    String placeholder = PlaceholderAPI.setBracketPlaceholders(player, parts[0]);

    if (parts.length < 3) {
      return placeholder;
    }

    String type = parts[1];
    String defaultVal = PlaceholderAPI.setBracketPlaceholders(player, parts[2]);
    if (placeholder == null || placeholder.isEmpty() || placeholder.equals(parts[0])) {
      return defaultVal;
    }

    switch (type) {
      case "STRING":
        return placeholder;
      case "INTEGER":
        try {
          Integer.parseInt(placeholder);
          return placeholder;
        } catch (NumberFormatException ex) {
          return defaultVal;
        }
      case "DOUBLE":
        try {
          Double.parseDouble(placeholder);
          return placeholder;
        } catch (NumberFormatException ex) {
          return defaultVal;
        }
      case "LONG":
        try {
          Long.parseLong(placeholder);
          return placeholder;
        } catch (NumberFormatException ex) {
          return defaultVal;
        }
    }
    return null;
  }
}
