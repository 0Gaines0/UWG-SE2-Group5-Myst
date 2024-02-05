import pandas as pd

df1 = pd.read_csv('steam-game-database.csv', delimiter=',', dtype={'appid': 'Int32'})
df2 = pd.read_csv('steam_media_data.csv', delimiter=',',dtype={'appid': 'Int32', 'header_image': str})

print("Unique appid values in df1:", df1['appid'].unique())
print("Unique appid values in df2:", df2['appid'].unique())

if 'appid' in df1.columns and 'appid' in df2.columns:
    merged_df = pd.merge(df1, df2, on='appid')
    merged_df.to_csv('output.csv', index=False)
else:
    print("Error: 'appid' column not found in one or both dataframes.")