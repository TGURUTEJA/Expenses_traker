'use client';
import React, { useEffect, useState } from "react";
import "./NavBar.css";
import { LogoutUser } from "../login/actions";

export default function Navbar({
    title = "Expenses Tracker",
    links = [
        { name: "Home", href: "/" },
        { name: "Expenses", href: "/expenses" },
        { name: "Add", href: "/add" },
        { name: "Settings", href: "/settings" },
    ],
}) {
    const [open, setOpen] = useState(true);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    useEffect(() => {
        const read = () => {
            const loggedIn = localStorage.getItem("isLoggedIn");
            setIsLoggedIn(loggedIn === "true");
        };

        // read initial value
        read();

        // storage fires on other windows/tabs
        const onStorage = (e) => {
            if (!e.key || e.key === "isLoggedIn") read();
        };

        // custom event to notify same-window updates (dispatch after changing localStorage)
        const onLocalChange = () => read();

        window.addEventListener("storage", onStorage);
        window.addEventListener("isLoggedInChanged", onLocalChange);

        return () => {
            window.removeEventListener("storage", onStorage);
            window.removeEventListener("isLoggedInChanged", onLocalChange);
        };
    }, []);
    return (
        <>
            {isLoggedIn && 
                <div>

                    {/* backdrop for mobile when open */ }
                    < div
                    className={`sidenav-backdrop ${open ? "show" : ""}`}
                    onClick={() => setOpen(false)}
                    aria-hidden={!open}
                    />

                    <nav
                        className={`sidenav ${open ? "open" : "closed"}`}
                        role="navigation"
                        aria-label="Main navigation"
                    >
                        <div className="brand" aria-hidden="false">
                            <span className="logo" aria-hidden="true" />
                            <span className="title">{title}</span>

                            <button
                                className="toggle"
                                onClick={() => setOpen((s) => !s)}
                                aria-expanded={open}
                                aria-label={open ? "Collapse sidebar" : "Expand sidebar"}
                                title={open ? "Collapse" : "Expand"}
                            >
                                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" aria-hidden>
                                    {open ? (
                                        <path d="M15 18l-6-6 6-6" stroke="currentColor" strokeWidth="1.6" strokeLinecap="round" strokeLinejoin="round" />
                                    ) : (
                                        <path d="M9 6l6 6-6 6" stroke="currentColor" strokeWidth="1.6" strokeLinecap="round" strokeLinejoin="round" />
                                    )}
                                </svg>
                            </button>
                        </div>

                        <div className="nav-list" role="menu" aria-hidden={!open && window?.innerWidth <= 640}>
                            {links.map((l) => (
                                <a
                                    key={l.href + l.name}
                                    href={l.href}
                                    className="nav-link"
                                    onClick={() => {
                                        // on mobile close after selection
                                        if (window?.innerWidth <= 640) setOpen(false);
                                    }}
                                    role="menuitem"
                                >
                                    <span className="icon" aria-hidden="true">{l.name.charAt(0)}</span>
                                    <span className="label">{l.name}</span>
                                </a>
                            ))}
                        </div>

                        <div className="footer-spacer" >
                            <button
                                className="logout"
                                onClick={LogoutUser}
                                aria-label="Log out"
                                title="Log out"
                            >
                                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" aria-hidden="true">
                                    <path d="M16 17l5-5-5-5M21 12H9" stroke="currentColor" strokeWidth="1.6" strokeLinecap="round" strokeLinejoin="round" />
                                    <path d="M13 19H6a2 2 0 0 1-2-2V7a2 2 0 0 1 2-2h7" stroke="currentColor" strokeWidth="1.6" strokeLinecap="round" strokeLinejoin="round" />
                                </svg><span className="label">Logout</span>

                            </button>
                        </div>

                    </nav>
                </div>
            }
        </>
    );
}