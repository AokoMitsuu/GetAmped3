	� G  ��       �W�n�F>�@�aK_��^���8 xp��ڤ?*|��ڊ���ʲ[�K��R��}� }���J�W'ANE`[Z���|3�5��5�U�J[�����圞�5O�I#jٶ(k�(�N�b�Q�M����yv�-�c�eّ�B25�)Y���3�X2y��w�Nd�J����^|w��d[T�TubY1y����*�T���͋��Hpڋ�jD��R*�q�6p�"���o��z
)��W$&�^���l�F�Y�����S%���\8A�u#ZqU�X5S�s�b�H���(���j8f��S�{Q���������ݫ�޽�ö�5?}���{�Ӄ�ȮYۺx�l����)/H]�� ��%�tQ�a����EӯcRM��XuIг4�.bF+��J�����y+Z��G���M��T�n(�U�{���c�62�����Ƀ�=���2��8X�zڍ�͞�K������.?�x�h��:(��SR{��)�����+ŔY�rk�}os�-儯�Bd��m_���eU��Z%+��:�ci#ZK�5@͂c�qNۄ�3�n��5������t�q9�G;���˲J��Ng_�ب1�༄t4lτǸ�p;�!�����ӯK�v����E��0Q�9Iρc�׵}�qG��;c����:�ڀ�9z�Cg}��.O��:��/{�b݄P�_��f���ӓ��,��qC�:׶(���xů�}�暇�z�ٿ��t?��k�ƫ�-�V��U�{��f�~ҷ��3�;����/������x�as9�2���G�������Y���ιj�J'�O/u&*юϊ��del����y(Ot}	84�"���QO�y@�?״}d�Eh�Y�/���f���i�����p,�:3*�x6��#������U��D4ɴ��d�F(���X�l�S�J�E+e%�=��>~B$u���(��BN�S<�$�������9�=Z�&��������YP'�(4��Z��w�   i  Lga2/stage/AmpedScript; e npc Lga2/data/NPCInfo; h id (Ljava/lang/String;I)I
 i j k BM1 mot npc1 Lkotori/data/KGLGenerationData; genD /icon/putout.agi�            ?�  ?�  ?�              A start rock 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V rock2 rock3 hit hitstage timeout setShadowState
 d { 3 rotate (Lkotori/geom/Quaternion;I)V
 f } ~�� kotori.geom.Quaternion?�              
 H 0 3 generateNPCView O(Lkotori/data/KGLGenerationData;Ljava/lang/String;I)Lkotori/gfx/KGLScriptModel;
 f � � Lkotori/gfx/KGLModel; � setEdge (Z)V
 � � � anime (FFFZ)V
 � � �B�  A    quake
 d � 3 	playSound (IIF)V
 d � �-        >L����?�              B�  -        �L����?�              -        �L����?�               	genoffset
 d � uD /efx/smoke/smoke_sand16.ptc�            ?�  ?�  ?�                p pos (FFF)V
 � � �>���>�� scale (F)V
 � � � generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �����?�33D /efx/stage/iwa_r/iwa_r.scm�    ����    ?���?   ?���              hpb
 � � � A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � � crack5B�  D /efx/shockwave/shockwave1.scm�    ����    ?�  ?�  ?�                start2D /efx/smoke/smoke_sand16.ptc�            ?�  ?�  ?�               D /efx/rock/rock01.scm�    �L��    ?��?��?��             �L��?�� start7D /efx/shockwave/shockwave1.scm�    ����    ?�  ?�  ?�                start1D /efx/stage/iwa_r/iwa_r.scm�    ����    ?�  ?   ?�               D #/efx/shockwave/shockwave_ground.scm�    =���    ?��?��?��             D /efx/smoke/smoke_sand16.ptc�            ?�  ?�  ?�               D /efx/stage/iwa_r/iwa_r.scm�    ����    ?���?   ?���              finishAllConnections
 d � 1D #/efx/shockwave/shockwave_ground.scm�    ����    >���>���>���             D #/efx/shockwave/shockwave_ground.scm�    ����    >���>���>���             -            ��?�               attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-    ?       � 2�D %/efx/etc/efx_circus/wolf3_efx_end.scm�    �       ?�  ?�  ?�  C4  �       � hit_fe d� �  '�`� N     ,�>�         kill
 f � 1 f g  n 
 q p - 1  m� l� o�r��  s 1  �*� |W*�� W*� �W*��� � �L+� +� �W+���� �W*(� �W*� �W*%b� �W*��� W*
� �W+���� �W*Z� �W*��� W*d� �W*��� W�  t u  r*+,� �W�N-��� �W-� �W*-� �W-���� �W-�� �W*-� �W�:� �W*�� �W�� �W*�� �W*��� �W*� �W�  v u  �*+,� �W�N-��� �W-� �W*-� �W-���� �W-�� �W*-� �W�:�� �W�� �W*�� �W��� �W�� �W*�� �W�:*� �W*�� �W*�� �W*��� �W*�� �W*� �W�  w u  i*+,� �W�N-��� �W-� �W*-� �W-���� �W-�� �W*-� �W�:� �W*�� �W�� �W*�� �W*� �W�  x 1  *� W�  y 1  *� W�  z 1  9*� �W*� �W*¶ �W*ö �W*��� W*�ʶ �W* ȶ �W*� �W� ޖ�    ?
=q=�\)?333?z�?n{� � click0�1    >�Q�    >W
=>�Q�>W
=� 	collision�1    >�33    >.{>�Q�>.{