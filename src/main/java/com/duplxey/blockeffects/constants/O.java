package com.duplxey.blockeffects.constants;

/**
 * Messages used by the plugin.
 */
public enum O {
    WRONG_SYNTAX(CC.V2 + "Wrong syntax! /blockeffects"),
    UNKNOWN_ARGS(CC.V2 + "Unknown arguments! /blockeffects"),
    NO_PERMISSION(CC.V2 + "You don't have the permission to do that!");

    private String text;

    O(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
