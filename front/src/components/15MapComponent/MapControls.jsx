import React, { useEffect } from "react";
import { Marker, Popup, useMap } from "react-leaflet";
import L, { Icon } from "leaflet";
import markerIcon from "../../images/burbuls.png";
import AuthContext from "../11Context/AuthContext";

export default function MapControls({ userCoordinates, userAddress }) {
  const { state } = React.useContext(AuthContext);

  const map = useMap();

  useEffect(() => {
    if (
      (state.role === "USER" &&
        userCoordinates !== "" &&
        userAddress.includes("Vilnius")) ||
      userAddress.includes("vilnius")
    ) {
      map.flyTo([userCoordinates.split(",")[1], userCoordinates.split(",")[0]]);
    }
  }, [userCoordinates]);

  return <div></div>;
}
