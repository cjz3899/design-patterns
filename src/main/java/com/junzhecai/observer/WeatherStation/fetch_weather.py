import json
import time
import urllib.request
from pathlib import Path

URL = (
    "https://api.open-meteo.com/v1/forecast"
    "?latitude=21.2719&longitude=110.3604"
    "&current=temperature_2m,relative_humidity_2m,surface_pressure"
    "&timezone=Asia%2FShanghai"
)


OUT = Path(__file__).parent / "weather.jsonl"
INTERVAL_SECONDS = 10


def fetch():
    with urllib.request.urlopen(URL, timeout=15) as res:
        cur = json.load(res)["current"]

    return {
        "time": cur["time"],
        "temperature": cur["temperature_2m"],
        "humidity": cur["relative_humidity_2m"],
        "pressure": cur["surface_pressure"],
    }


def main():
    while True:
        try:
            row = fetch()
            with OUT.open("a", encoding="utf-8") as f:
                f.write(json.dumps(row, ensure_ascii=False) + "\n")
            print(row)
        except Exception as e:
            print(f"fetch failed: {e}", flush=True)
        time.sleep(INTERVAL_SECONDS)


if __name__ == "__main__":
    main()
