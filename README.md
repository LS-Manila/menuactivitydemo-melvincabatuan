# Menu Activity Demo 

menuactivitydemo-melvincabatuan created by Classroom for GitHub

This assignment presents a basic Menu activity implementation using Expandable lists.


## Problem:

Design and implement an Android Menu activity using Expandable lists.   


## Accept

To accept the assignment, click the following URL:

https://classroom.github.com/assignment-invitations/7fe4aa96fd3a3516ede4f3972ecb9fc0

## Sample Solution:

https://github.com/DeLaSalleUniversity-Manila/menuactivitydemo-melvincabatuan

## Keypoint:

In the MainActivity.java:
```Java
  drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerList = (ExpandableListView) findViewById(R.id.left_drawer);

        drawerList.setAdapter(new MyExpandableListAdapter(this, groupItem, childItem));

        drawerList.setOnChildClickListener(this);

        drawerList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(MainActivity.this, "Clicked On " + groupPosition, Toast.LENGTH_SHORT).show();
                talk("Clicked On Option " + groupPosition);
            }
        });
        
        ...
        
            @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {
        Toast.makeText(this, "Clicked On Child " + groupPosition + "-" + childPosition,
                Toast.LENGTH_SHORT).show();
        talk("Clicked On Child " + groupPosition + "-" + childPosition);
        return true;
    }
```

In my ExpandableListAdapter.java:
```Java
public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    private HashMap<String, List<String>> _listDataChild;
    private TextView lblListHeader;
    private List<Integer> _childClicks;


    public MyExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;

    }


    public MyExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData, int score[], int numberOfQuestions) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);


        txtListChild.setText(Html.fromHtml(childText).toString());
        return convertView;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);

        if (isExpanded) {
            lblListHeader.setBackgroundColor(Color.parseColor("#006400")); //GREEN
            lblListHeader.setTextColor(Color.WHITE);
        } else {
            //lblListHeader.setBackgroundColor(Color.BLACK);
        }


        //lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(Html.fromHtml(headerTitle).toString());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
```


## Submission Procedure with Git: 

```shell
$ cd /path/to/your/android/app/
$ git init
$ git add –all
$ git commit -m "your message, e.x. Assignment 1 submission"
$ git remote add origin <Assignment link copied from assignment github, e.x. https://github.com/DeLaSalleUniversity-Manila/secondactivityassignment-melvincabatuan.git>
$ git push -u origin master
<then Enter Username and Password>
```

Sample:

https://gist.github.com/melvincabatuan/69dd33f74d1b34d81b9f

## Screenshots:

![alt tag](https://github.com/DeLaSalleUniversity-Manila/menuactivitydemo-melvincabatuan/blob/master/device-2015-10-05-224516.png)

![alt tag](https://github.com/DeLaSalleUniversity-Manila/menuactivitydemo-melvincabatuan/blob/master/device-2015-10-05-224558.png)

![alt tag](https://github.com/DeLaSalleUniversity-Manila/menuactivitydemo-melvincabatuan/blob/master/device-2015-10-05-224642.png)

![alt tag](https://github.com/DeLaSalleUniversity-Manila/menuactivitydemo-melvincabatuan/blob/master/device-2015-10-05-224715.png)

![alt tag](https://github.com/DeLaSalleUniversity-Manila/menuactivitydemo-melvincabatuan/blob/master/device-2015-10-05-224809.png)

"*Life is not about waiting for the storm to pass … it’s about learning to dance in the rain.*" - Evelyn Beatrice Hall
