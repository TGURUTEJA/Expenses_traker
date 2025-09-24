'use client';
import { useEffect, useState } from "react";
import { getData } from "./actions";
import Navbar from "../components/navbar";
type UserCred = {
  id?: string;
  name?: string;
  email?: string;
};

export default function DashboardPage() {
  const [data, setData] = useState<UserCred | null>(null);
    useEffect(() => {
       getData().then(data => {
            setData(data);
            console.log("Data fetched:", data);
        }).catch(err => {
            console.error("Error fetching data:", err);
            window.location.href = '/login';
        });
        
    }, []);
  return (
    <>
    <div className="flex h-screen items-center justify-center">
      <h1 className="text-3xl font-bold ">Welcome to the Dashboard</h1>
      <p >{JSON.stringify(data)}</p>
    </div>
    </>
  );
}

